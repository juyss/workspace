package com.icepoint.framework.autoconfigure.web.security.customizer;

import com.icepoint.framework.autoconfigure.web.security.WebSecurityProperties;
import com.icepoint.framework.autoconfigure.web.security.constant.Constants;
import com.icepoint.framework.web.core.message.MessageContext;
import com.icepoint.framework.web.core.response.ResponseBuilder;
import com.icepoint.framework.web.core.response.ResponseUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Optional;

/**
 * 登录验证配置器，包含了几个默认的处理器：登录成功处理器、登录失败处理器。
 * <p>
 * 登录方式默认为表单登录，可以通过配置文件的icepoint.web.security.login-type进行自定义。
 * <p>
 * 要配置自定义的登录成功处理器，实现{@link AuthenticationSuccessHandler}并将其注册为Spring Bean。
 * 要配置自定义的登录失败处理器，实现{@link AuthenticationFailureHandler}并将其注册为Spring Bean。
 *
 * @author Jiawei Zhao
 */
public class LoginConfigurer implements HttpSecurityConfigurer {

    private final MessageContext messageContext;
    private final WebSecurityProperties properties;
    private static final String LOGIN_PROCESS_URL = Constants.LOGIN_PATH;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public LoginConfigurer(MessageContext messageContext,
            WebSecurityProperties properties,
            Optional<AuthenticationSuccessHandler> authenticationSuccessHandler,
            Optional<AuthenticationFailureHandler> authenticationFailureHandler) {

        this.messageContext = messageContext;
        this.properties = properties;

        this.authenticationSuccessHandler = authenticationSuccessHandler
                .orElseGet(() -> (request, response, authentication) -> ResponseUtils
                        .writeJson(authentication.getPrincipal(), response));

        this.authenticationFailureHandler = authenticationFailureHandler
                .orElseGet(() -> (request, response, exception) -> {

                    Exception realException = exception instanceof InternalAuthenticationServiceException
                            ? (Exception) exception.getCause()
                            : exception;

                    String code = this.messageContext.getCode(realException);

                    ResponseUtils.writeJson(ResponseBuilder.any(null)
                            .code(code)
                            .message(messageContext.getMessage(realException, code))
                            .build(), response);
                });
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        switch (properties.getLoginType()) {
            case FORM:
                formLogin(http);
                break;
            case OAUTH2:
                oauth2Login(http);
                break;
            default:
                throw new IllegalStateException("不支持的登录类型: " + properties.getLoginType());
        }
    }

    private void formLogin(HttpSecurity http) throws Exception {
        http.formLogin(configurer -> configurer
                .loginProcessingUrl(LOGIN_PROCESS_URL)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler));
    }

    private void oauth2Login(HttpSecurity http) throws Exception {
        http.oauth2Login(configurer -> configurer
                .loginProcessingUrl(LOGIN_PROCESS_URL)
                .successHandler(authenticationSuccessHandler));
    }
}
