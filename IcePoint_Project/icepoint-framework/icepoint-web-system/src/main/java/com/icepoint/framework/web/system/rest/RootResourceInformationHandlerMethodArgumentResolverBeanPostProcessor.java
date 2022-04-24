package com.icepoint.framework.web.system.rest;

import com.icepoint.framework.core.support.ApplicationContextAwareBeanPostProcessor;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.FieldUtils;
import com.icepoint.framework.web.system.expression.DefaultExpressionContext;
import com.icepoint.framework.web.system.expression.Expression;
import com.icepoint.framework.web.system.expression.ExpressionContext;
import com.icepoint.framework.web.system.expression.parser.ExpressionParser;
import com.icepoint.framework.web.system.expression.path.EntityPathProxy;
import com.icepoint.framework.web.system.expression.path.EntityPathProxyResponseProcessor;
import com.icepoint.framework.web.system.expression.querydsl.ExpressionPredicateBuilderAdapter;
import com.mysema.commons.lang.Pair;
import com.querydsl.core.support.PathsExtractor;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeansException;
import org.springframework.core.MethodParameter;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.QuerydslRepositoryInvokerAdapter;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.QuerydslBindingsFactory;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.querydsl.binding.QuerydslPredicateBuilder;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.repository.support.RepositoryInvoker;
import org.springframework.data.repository.support.RepositoryInvokerFactory;
import org.springframework.data.rest.webmvc.RootResourceInformation;
import org.springframework.data.rest.webmvc.config.ResourceMetadataHandlerMethodArgumentResolver;
import org.springframework.data.rest.webmvc.config.RootResourceInformationHandlerMethodArgumentResolver;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.Lazy;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.*;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.*;

/**
 * @author Jiawei Zhao
 */
@Component
public class RootResourceInformationHandlerMethodArgumentResolverBeanPostProcessor
        extends ApplicationContextAwareBeanPostProcessor {

    private static final String BEAN_CLASS = "org.springframework.data.rest.webmvc.config.QuerydslAwareRootResourceInformationHandlerMethodArgumentResolver";

    private static final String QUERY = "_query";

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (!bean.getClass().getName().equals(BEAN_CLASS)) {
            return bean;
        }

        RootResourceInformationHandlerMethodArgumentResolver resolver = CastUtils.cast(bean);

        QuerydslPredicateBuilder predicateBuilder = FieldUtils.getRequiredField(resolver, "predicateBuilder");
        Repositories repositories = FieldUtils.getRequiredField(resolver, "repositories");
        RepositoryInvokerFactory invokerFactory = FieldUtils.getRequiredField(resolver, "invokerFactory");
        ResourceMetadataHandlerMethodArgumentResolver resourceMetadataResolver = FieldUtils
                .getRequiredField(resolver, "resourceMetadataResolver");
        QuerydslBindingsFactory factory = FieldUtils.getRequiredField(resolver, "factory");

        ExpressionParser expressionParser = ExpressionParser.INSTANCE;
        Lazy<ExpressionContext> context = Lazy.of(() -> getApplicationContext().getBean(ExpressionContext.class))
                .or(DefaultExpressionContext::new);
        ExpressionPredicateBuilderAdapter adapter = ExpressionPredicateBuilderAdapter.INSTANCE;

        return new RootResourceInformationHandlerMethodArgumentResolver(repositories, invokerFactory, resourceMetadataResolver) {

            @Override
            public RootResourceInformation resolveArgument(MethodParameter parameter,
                    ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                    WebDataBinderFactory binderFactory) throws Exception {

                RootResourceInformation information = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
                Assert.notNull(information, "找不到对应的参数: RootResourceInformation");

                if (webRequest.getParameterMap().containsKey(EntityPathProxyResponseProcessor.PATHS)) {

                    webRequest.setAttribute(EntityPathProxy.ROOT_TYPE, information.getDomainType(),
                            RequestAttributes.SCOPE_REQUEST);
                }

                return information;
            }

            @Override
            protected RepositoryInvoker postProcess(MethodParameter parameter, RepositoryInvoker invoker,
                    Class<?> domainType, Map<String, String[]> parameters) {

                if (!parameter.hasParameterAnnotation(QuerydslPredicate.class)) {
                    return invoker;
                }

                return repositories.getRepositoryFor(domainType)
                        .filter(QuerydslPredicateExecutor.class::isInstance)
                        .map(QuerydslPredicateExecutor.class::cast)
                        .flatMap(it -> getRepositoryAndPredicate(it, domainType, parameters))
                        .map(it -> getQuerydslAdapter(invoker, it.getFirst(), it.getSecond()))
                        .orElse(invoker);
            }

            private Optional<Pair<QuerydslPredicateExecutor<?>, Predicate>> getRepositoryAndPredicate(
                    QuerydslPredicateExecutor<?> repository, Class<?> domainType, Map<String, String[]> parameters) {

                ClassTypeInformation<?> type = ClassTypeInformation.from(domainType);

                QuerydslBindings bindings = factory.createBindingsFor(type);
                Predicate queryPredicate = parseQuery(type, bindings, parameters);
                Predicate querydslPredicate = predicateBuilder.getPredicate(type, toMultiValueMap(parameters), bindings);

                return Optional.ofNullable(ExpressionUtils.and(querydslPredicate, queryPredicate))
                        .map(it -> Pair.of(repository, it));
            }

            @Nullable
            private Predicate parseQuery(ClassTypeInformation<?> type,
                    QuerydslBindings bindings,
                    Map<String, String[]> parameters) {

                String[] query;
                String queryExpression;

                if ((query = parameters.get(QUERY)) != null
                        && query.length > 0
                        && StringUtils.hasText(queryExpression = query[0])) {

                    Assert.isTrue(query.length == 1, "_query参数不能多于1个");

                    Expression expression = expressionParser.parseExpression(queryExpression, context.get());
                    Predicate predicate = adapter.get(expression).getPredicate(type, expression, context.get());

                    List<Path<?>> paths = new ArrayList<>();
                    predicate.accept(PathsExtractor.DEFAULT, paths);

                    if (!CollectionUtils.isEmpty(paths)) {
                        bindings.excluding(paths.toArray(new Path[0]));
                    }

                    return predicate;

                } else {
                    return null;
                }
            }

            private RepositoryInvoker getQuerydslAdapter(RepositoryInvoker invoker,
                    QuerydslPredicateExecutor<?> repository, Predicate predicate) {
                return new QuerydslRepositoryInvokerAdapter(invoker, CastUtils.cast(repository), predicate);
            }

            private MultiValueMap<String, String> toMultiValueMap(Map<String, String[]> source) {

                MultiValueMap<String, String> result = new LinkedMultiValueMap<>();

                for (Map.Entry<String, String[]> entry : source.entrySet()) {
                    result.put(entry.getKey(), Arrays.asList(entry.getValue()));
                }

                return result;
            }
        };
    }
}
