package com.icepoint.framework.web.system.rest;

import com.icepoint.framework.core.support.ApplicationContextAwareBeanPostProcessor;
import com.icepoint.framework.core.util.FieldUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.core.MethodParameter;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.core.mapping.ResourceMappings;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.data.rest.webmvc.BaseUri;
import org.springframework.data.rest.webmvc.config.ResourceMetadataHandlerMethodArgumentResolver;
import org.springframework.data.rest.webmvc.util.UriUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@Component
public class ResourceMetadataHandlerMethodArgumentResolverBeanPostProcessor extends ApplicationContextAwareBeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (!(bean instanceof ResourceMetadataHandlerMethodArgumentResolver)) {
            return bean;
        }

        ResourceMetadataHandlerMethodArgumentResolver resolver = (ResourceMetadataHandlerMethodArgumentResolver) bean;

        Repositories repositories = FieldUtils.getRequiredField(resolver, "repositories");
        ResourceMappings mappings = FieldUtils.getRequiredField(resolver, "mappings");
        BaseUri baseUri = FieldUtils.getRequiredField(resolver, "baseUri");

        return new ResourceMetadataHandlerMethodArgumentResolver(repositories, mappings, baseUri) {

            @Nullable
            @Override
            public ResourceMetadata resolveArgument(
                    MethodParameter parameter, ModelAndViewContainer mavContainer,
                    NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

                String repositoryKey = (String) webRequest.getAttribute(
                        MultiplyPathsRepositoryRestHandlerMapping.MULTIPLY_PATH,
                        RequestAttributes.SCOPE_REQUEST);

                if (repositoryKey == null) {

                    Method parameterMethod = parameter.getMethod();
                    assert parameterMethod != null;

                    String lookupPath = baseUri.getRepositoryLookupPath(webRequest);
                    repositoryKey = UriUtils.findMappingVariable("repository", parameterMethod, lookupPath);
                }

                Assert.hasText(repositoryKey, "无法解析Repository接口");

                for (Class<?> domainType : repositories) {
                    ResourceMetadata mapping = mappings.getMetadataFor(domainType);
                    if (mapping.getPath().matches(repositoryKey) && mapping.isExported()) {
                        return mapping;
                    }
                }

                throw new IllegalArgumentException(String.format("Could not resolve repository metadata for %s.", repositoryKey));
            }
        };

    }
}
