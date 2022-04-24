package com.icepoint.framework.autoconfigure;

import com.icepoint.framework.core.util.ApplicationContextUtils;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
@ConditionalOnClass(ApplicationContextUtils.class)
@ComponentScan(IcepointFrameworkAutoConfiguration.BASE_PACKAGE)
@AutoConfigurationPackage(basePackages = IcepointFrameworkAutoConfiguration.BASE_PACKAGE)
@Configuration(proxyBeanMethods = false)
public class IcepointFrameworkAutoConfiguration extends IcepointFrameworkBaseConfiguration {

    public static final String BASE_PACKAGE = "com.icepoint.framework.core";

    @Bean
    public PackageScanRegistry repositoryRegistry(List<PackageScanConfigurer> configurers) {

        PackageScanRegistry registry = new PackageScanRegistry();
        registry.addBasePackages(BASE_PACKAGE);
        registry.addBasePackages(AutoConfigurationPackages.get(getBeanFactory()));

        if (!CollectionUtils.isEmpty(configurers)) {
            for (PackageScanConfigurer configurer : configurers) {
                configurer.configure(registry);
            }
        }

        return registry;
    }
}
