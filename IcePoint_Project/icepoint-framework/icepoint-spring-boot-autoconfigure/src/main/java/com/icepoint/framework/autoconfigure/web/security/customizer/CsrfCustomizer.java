package com.icepoint.framework.autoconfigure.web.security.customizer;


import com.icepoint.framework.autoconfigure.web.security.DelegatingWebSecurityProperties;
import com.icepoint.framework.core.util.EnvironmentUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * 开发环境默认关闭csrf验证
 *
 * @author Jiawei Zhao
 */
public class CsrfCustomizer implements Customizer<CsrfConfigurer<HttpSecurity>>, EnvironmentAware {

    private Environment environment;

    private final DelegatingWebSecurityProperties properties;

    public CsrfCustomizer(DelegatingWebSecurityProperties properties) {
        this.properties = properties;
    }

    @Override
    public void customize(CsrfConfigurer<HttpSecurity> configurer) {

        if (EnvironmentUtils.isDev(environment)) {
            configurer.disable();
        } else {
            configurer.ignoringAntMatchers(properties.getIgnorePaths().toArray(new String[0]))
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
