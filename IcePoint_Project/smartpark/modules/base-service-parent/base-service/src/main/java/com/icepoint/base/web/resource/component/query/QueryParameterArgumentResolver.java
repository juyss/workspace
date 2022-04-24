package com.icepoint.base.web.resource.component.query;

import com.icepoint.base.web.resource.util.QueryParameterUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

import java.util.Collections;

public class QueryParameterArgumentResolver extends AbstractMessageConverterMethodArgumentResolver {

    private static final String DEFAULT_MATCH_PARAMETER = "$match";
    private static final String DEFAULT_REQUIRE_PARAMETER = "$require";

    private @Getter
    @Setter
    String matchParameterName = DEFAULT_MATCH_PARAMETER;
    private @Getter
    @Setter
    String requireParameterName = DEFAULT_REQUIRE_PARAMETER;

    public QueryParameterArgumentResolver() {
        super(Collections.singletonList(new UnsupprotedHttpMessageConverter<>()));
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return QueryParameter.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) {

        GenericQueryParameter queryParameter = new GenericQueryParameter();

        String matchStr = webRequest.getParameter(getMatchParameterName());
        queryParameter.setMatch(QueryParameterUtils.parseMatch(matchStr).orElse(null));

        String requireStr = webRequest.getParameter(getRequireParameterName());
        queryParameter.setRequire(QueryParameterUtils.parseRequire(requireStr).orElse(null));

        return queryParameter;
    }


}
