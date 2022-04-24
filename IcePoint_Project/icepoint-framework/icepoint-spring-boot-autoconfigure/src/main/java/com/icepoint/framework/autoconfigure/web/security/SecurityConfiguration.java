package com.icepoint.framework.autoconfigure.web.security;

import com.icepoint.framework.autoconfigure.web.security.customizer.*;
import org.springframework.data.util.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Jiawei Zhao
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Lazy<UserDetailsService> userDetailsService;
    private final Lazy<CsrfCustomizer> csrfCustomizer;
    private final Lazy<AuthorizeRequestCustomizer> authorizeRequestCustomizer;
    private final Lazy<LoginConfigurer> loginConfigurer;
    private final Lazy<LogoutCustomizer> logoutCustomizer;
    private final Lazy<ExceptionHandlingCustomizer> exceptionHandlingCustomizer;

    public SecurityConfiguration() {

        this.userDetailsService = Lazy.of(() -> getApplicationContext().getBeanProvider(UserDetailsService.class)
                .getIfAvailable());
        this.csrfCustomizer = Lazy.of(() -> getApplicationContext().getBeanProvider(CsrfCustomizer.class)
                .getIfAvailable());
        this.authorizeRequestCustomizer = Lazy.of(() -> getApplicationContext().getBeanProvider(AuthorizeRequestCustomizer.class)
                .getIfAvailable());
        this.loginConfigurer = Lazy.of(() -> getApplicationContext().getBeanProvider(LoginConfigurer.class)
                .getIfAvailable());
        this.logoutCustomizer = Lazy.of(() -> getApplicationContext().getBeanProvider(LogoutCustomizer.class)
                .getIfAvailable());
        this.exceptionHandlingCustomizer = Lazy.of(() -> getApplicationContext().getBeanProvider(ExceptionHandlingCustomizer.class)
                .getIfAvailable());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (csrfCustomizer.getOptional().isPresent()) {
            http.csrf(csrfCustomizer.get());
        }

        if (authorizeRequestCustomizer.getOptional().isPresent()) {
            http.authorizeRequests(authorizeRequestCustomizer.get());
        }

        if (loginConfigurer.getOptional().isPresent()) {
            loginConfigurer.get().configure(http);
        }

        if (logoutCustomizer.getOptional().isPresent()) {
            http.logout(logoutCustomizer.get());
        }

        if (exceptionHandlingCustomizer.getOptional().isPresent()) {
            http.exceptionHandling(exceptionHandlingCustomizer.get());
        }

        if (userDetailsService.getOptional().isPresent()) {
            http.userDetailsService(userDetailsService.get());
        }
    }
}
