package com.icepoint.framework.web.system.support;

import com.icepoint.framework.core.io.Serializers;
import com.icepoint.framework.core.support.TypeReferences;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.ResourceModelConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class ResourceModelHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final LookupHandlerMethodArgumentResolver lookupResolver;

    private final ResourceModelConverter converter;

    public ResourceModelHandlerMethodArgumentResolver(
            LookupHandlerMethodArgumentResolver lookupResolver,
            ResourceModelConverter converter) {
        this.lookupResolver = lookupResolver;
        this.converter = converter;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ResourceModel.class == parameter.getParameterType();
    }

    @NonNull
    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

        Lookup lookup = lookupResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);

        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.state(servletRequest != null, "没有HttpServletRequest");
        ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);

        Map<String, Object> map = Serializers.json()
                .deserialize(inputMessage.getBody(), TypeReferences.STRING_OBJECT_MAP);

        return converter.convert(lookup, map);
    }
}
