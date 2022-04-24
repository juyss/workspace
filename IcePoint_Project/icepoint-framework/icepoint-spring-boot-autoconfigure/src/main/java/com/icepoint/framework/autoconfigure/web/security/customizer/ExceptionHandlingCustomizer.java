package com.icepoint.framework.autoconfigure.web.security.customizer;

import com.icepoint.framework.web.core.message.MessageContext;
import com.icepoint.framework.web.core.response.ResponseBuilder;
import com.icepoint.framework.web.core.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Jiawei Zhao
 */
public class ExceptionHandlingCustomizer implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {

    private final MessageContext messageContext;

    @Autowired
    public ExceptionHandlingCustomizer(MessageContext messageContext) {
        this.messageContext = messageContext;
    }

    @Override
    public void customize(ExceptionHandlingConfigurer<HttpSecurity> configurer) {

        configurer
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDenyHandler());
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, exception) -> {
            String code = messageContext.getCode(exception);
            ResponseUtils.writeJson(ResponseBuilder.any(null)
                    .code(code)
                    .message(messageContext.getMessage(code))
                    .build(), response);
            exception.printStackTrace();
        };
    }

    private AccessDeniedHandler accessDenyHandler() {
        return (request, response, exception) -> {
            String code = messageContext.getCode(exception);
            ResponseUtils.writeJson(ResponseBuilder.any(null)
                    .code(code)
                    .message(messageContext.getMessage(code))
                    .build(), response);
            exception.printStackTrace();
        };
    }
}
