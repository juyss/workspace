package com.icepoint.framework.web.system.support;

import com.icepoint.framework.web.system.resource.ResourceModelMetadata;
import com.icepoint.framework.web.system.service.ResourceMetadataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Nullable;

/**
 * @author Jiawei Zhao
 */
@Slf4j
@RequiredArgsConstructor
public class ResourceModelMetadataHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String ASSET_CODE_PARAMETER = "_assetCode";

    private final ResourceMetadataService service;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == ResourceMetadata.class;
    }

    @NonNull
    @Override
    public ResourceModelMetadata resolveArgument(MethodParameter parameter,
            @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

        String assetCode = webRequest.getParameter(ASSET_CODE_PARAMETER);
        Assert.hasText(assetCode, "assetCode为空，无锡解析ResourceModelMetadata参数");

        return service.findByAssetCode(assetCode);
    }

}
