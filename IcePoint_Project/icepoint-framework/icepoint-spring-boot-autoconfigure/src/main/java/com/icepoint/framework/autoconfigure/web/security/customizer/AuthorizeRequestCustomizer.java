package com.icepoint.framework.autoconfigure.web.security.customizer;

import com.icepoint.framework.autoconfigure.web.security.DelegatingWebSecurityProperties;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author Jiawei Zhao
 */
public class AuthorizeRequestCustomizer implements Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry> {

    private final DelegatingWebSecurityProperties properties;

    public AuthorizeRequestCustomizer(DelegatingWebSecurityProperties properties) {
        this.properties = properties;
    }

    @SuppressWarnings("SpringElInspection")
    @Override
    public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {

        registry
                .antMatchers(properties.getIgnorePaths().toArray(new String[0]))
                .permitAll()
                .anyRequest()
                .access("isAuthenticated() and @roleService.hasRole(request, authentication)");
    }
}
