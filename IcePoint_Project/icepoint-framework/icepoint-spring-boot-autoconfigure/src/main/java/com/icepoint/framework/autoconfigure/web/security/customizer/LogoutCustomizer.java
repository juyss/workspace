package com.icepoint.framework.autoconfigure.web.security.customizer;

import com.icepoint.framework.web.core.message.MessageContext;
import com.icepoint.framework.web.core.response.ResponseBuilder;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.core.util.CoreMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author Jiawei Zhao
 */
public class LogoutCustomizer implements Customizer<LogoutConfigurer<HttpSecurity>> {

    private final MessageContext messageContext;

    @Autowired
    public LogoutCustomizer(MessageContext messageContext) {
        this.messageContext = messageContext;
    }

    @Override
    public void customize(LogoutConfigurer<HttpSecurity> configurer) {

        // TODO: 做成可配置
        String logoutUrl = "/auth/logout";

        configurer
                .logoutUrl(logoutUrl)
                .logoutSuccessHandler(logoutSuccessHandler());
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> ResponseUtils
                .writeJson(ResponseBuilder.any(null)
                        .code(CoreMessage.OK.getCode())
                        .message(messageContext.getMessage(CoreMessage.OK))
                        .build(), response);
    }
}
