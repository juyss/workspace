package com.icepoint.framework.autoconfigure.web.security;

import com.icepoint.framework.autoconfigure.IcepointFrameworkBaseConfiguration;
import com.icepoint.framework.autoconfigure.PackageScanRegistry;
import com.icepoint.framework.autoconfigure.data.DataAutoConfiguration;
import com.icepoint.framework.web.security.entity.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Jiawei Zhao
 */
@Import(CustomersRegistrar.class)
@AutoConfigureBefore({ DataAutoConfiguration.class })
@EnableConfigurationProperties(WebSecurityProperties.class)
@ComponentScan(SecurityAutoConfiguration.BASE_PACKAGE)
@AutoConfigurationPackage(basePackages = SecurityAutoConfiguration.BASE_PACKAGE)
@ConditionalOnClass(User.class)
@Configuration
public class SecurityAutoConfiguration extends IcepointFrameworkBaseConfiguration {

    public static final String BASE_PACKAGE = "com.icepoint.framework.web.security";

    @Override
    public void configure(PackageScanRegistry registry) {
        registry.addBasePackages(BASE_PACKAGE);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityConfiguration securityConfiguration() {
        return new SecurityConfiguration();
    }

    @Primary
    @Bean
    public WebSecurityProperties webSecurityProperties() {
        return new WebSecurityProperties();
    }

    @Bean
    public DelegatingWebSecurityProperties configuredWebSecurityProperties(
            WebSecurityProperties webSecurityProperties,
            ObjectProvider<WebSecurityPropertiesCustomizer> customizers) {


        customizers.orderedStream().forEach(customizer -> customizer.customize(webSecurityProperties));
        return new DelegatingWebSecurityProperties(webSecurityProperties);
    }

}
