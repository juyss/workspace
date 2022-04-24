package com.icepoint.framework.web.system.expression.path;

import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.FieldUtils;
import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.core.util.NullablePair;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseProcessor;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.expression.*;
import com.icepoint.framework.web.system.expression.node.EntityPath;
import com.icepoint.framework.web.system.expression.node.LogicConnector;
import com.icepoint.framework.web.system.expression.node.LogicalAnd;
import com.icepoint.framework.web.system.expression.parser.ExpressionParser;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.projection.ProjectionDefinitions;
import org.springframework.data.rest.core.support.SelfLinkProvider;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.mapping.Associations;
import org.springframework.data.rest.webmvc.support.PersistentEntityProjector;
import org.springframework.data.util.Optionals;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
@Order
@Component
public class EntityPathProxyResponseProcessor
        implements ResponseProcessor<Response<?>>, BeanClassLoaderAware {

    public static final String PATHS = "_paths";

    private final ProxyAdapter adapter = new DefaultProxyAdapter();

    private final ExpressionContext expressionContext;

    private final ExpressionParser parser;

    private final PersistentEntities entities;

    private final Associations associations;

    private final Optional<ProjectionDefinitions> projectionDefinitions;

    private final SelfLinkProvider linkProvider;

    private final Optional<ProjectionFactory> projectionFactory;

    private ClassLoader beanClassLoader;

    private final ApplicationContext applicationContext;

    public EntityPathProxyResponseProcessor(
            ApplicationContext applicationContext,
            PersistentEntities entities,
            Associations associations,
            SelfLinkProvider linkProvider,
            Optional<ProjectionFactory> projectionFactory,
            Optional<ProjectionDefinitions> projectionDefinitions,
            Optional<ExpressionContext> expressionContext) {

        this.parser = ExpressionParser.INSTANCE;
        this.entities = entities;
        this.associations = associations;
        this.linkProvider = linkProvider;
        this.applicationContext = applicationContext;

        this.expressionContext = expressionContext.orElseGet(DefaultExpressionContext::new);

        this.projectionDefinitions = Optionals.firstNonEmpty(
                () -> projectionDefinitions,
                () -> {
                    Assert.notNull(this.applicationContext, MessageTemplates.notNull("applicationContext"));
                    RepositoryRestConfiguration restConfiguration =
                            applicationContext.getBean(RepositoryRestConfiguration.class);
                    return Optional.of(FieldUtils.getRequiredField(restConfiguration, "projectionConfiguration"));
                }
        );

        this.projectionFactory = Optionals.firstNonEmpty(
                () -> projectionFactory,
                () -> {
                    SpelAwareProxyProjectionFactory factory = new SpelAwareProxyProjectionFactory();
                    factory.setBeanFactory(applicationContext);
                    factory.setBeanClassLoader(beanClassLoader);
                    return Optional.of(factory);
                }
        );
    }

    @Override
    public Response<?> process(Response<?> body, ServerHttpRequest request, ServerHttpResponse response) {

        if (!(request instanceof ServletServerHttpRequest)) {
            return body;
        }

        Object data = body.getData();
        if (data == null) {
            return body;
        }

        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

        Class<?> rootType = getRootType(servletRequest);
        String paths = servletRequest.getParameter(PATHS);

        if (!StringUtils.hasText(paths) || rootType == null) {
            rootType = ResponseUtils.getContentBeanType(data);
            paths = "";

            if (rootType == null) {
                return body;
            }
        }

        List<EntityPath> entityPaths = parseEntityPaths(paths);

        data = proxy(rootType, body, data, entityPaths, servletRequest);
        body.setData(CastUtils.cast(data));

        return body;
    }

    @Nullable
    private Object proxy(Class<?> rootType, Response<?> body, Object data, List<EntityPath> entityPaths,
            HttpServletRequest request) {

        if (ResponseUtils.isCollectionLikeResponse(body)) {

            Collection<Object> collection = CastUtils.cast(data);

            if (CollectionUtils.isEmpty(collection)) {
                return data;
            }

            data = collection.stream()
                    .map(it -> doProxy(rootType, it, entityPaths, request))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } else {

            data = doProxy(rootType, data, entityPaths, request);
        }

        return data;
    }

    @Nullable
    private Object doProxy(Class<?> rootType, Object entity, List<EntityPath> paths, HttpServletRequest request) {

        if (entity instanceof EntityModel) {
            Object unwrapped = ResponseUtils.unwrapContent(entity);
            if (unwrapped == null) {
                return null;
            }

            Object proxy = adapter.proxy(rootType, unwrapped, paths, null, getAssembler(request), entities);

            return proxy == unwrapped
                    ? entity
                    : EntityModel.of(proxy, ((EntityModel<?>) entity).getLinks());
        }

        return adapter.proxy(rootType, entity, paths, null, getAssembler(request), entities);
    }

    private List<EntityPath> parseEntityPaths(String paths) {

        if (!StringUtils.hasText(paths)) {
            return Collections.emptyList();
        }

        Expression expression = parser.parseExpression(paths, expressionContext);
        Assert.isInstanceOf(ChainExpression.class, expression, "_path解析结果类型必须为ChainExpression");

        List<EntityPath> result = new ArrayList<>();
        for (NullablePair<Expression, LogicConnector> pair : ((ChainExpression) expression)) {

            Expression expr = pair.getFirst();
            Assert.notNull(expr, "表达式不能为空");
            LogicConnector connector = pair.getSecond();

            Assert.isInstanceOf(EntityPathOnlyExpression.class, expr, "_path参数只能传入实体路径");
            if (connector != null) {
                Assert.isTrue(connector.getClass() == LogicalAnd.class, "_path的连接符必须是&&");
            }

            EntityPath entityPath = ((EntityPathOnlyExpression) expr).getEntityPath();
            result.add(entityPath);
        }

        return result;
    }

    @Nullable
    private Class<?> getRootType(HttpServletRequest request) {

        Object attribute = request.getAttribute(EntityPathProxy.ROOT_TYPE);

        return attribute instanceof Class
                ? (Class<?>) attribute
                : null;
    }

    @Nullable
    private PersistentEntityResourceAssembler getAssembler(HttpServletRequest request) {

        ProjectionDefinitions definitions = this.projectionDefinitions.orElse(null);
        ProjectionFactory factory = projectionFactory.orElse(null);

        if (definitions == null || factory == null) {
            return null;
        }

        String parameter = request.getParameter(definitions.getParameterName());
        PersistentEntityProjector projector = new PersistentEntityProjector(definitions, factory,
                parameter, associations.getMappings());

        return new PersistentEntityResourceAssembler(entities, projector, associations, linkProvider);
    }

    @Override
    public boolean supports(Class<?> type) {
        return Response.class.isAssignableFrom(type);
    }


    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }
}
