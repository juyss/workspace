package com.icepoint.framework.autoconfigure.web.ui;

import com.icepoint.framework.autoconfigure.IcepointFrameworkBaseConfiguration;
import com.icepoint.framework.autoconfigure.PackageScanRegistry;
import com.icepoint.framework.autoconfigure.data.DataAutoConfiguration;
import com.icepoint.framework.web.ui.entity.UiMenu;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jiawei Zhao
 */

@ComponentScan(WebUiAutoConfiguration.BASE_PACKAGE)
@AutoConfigurationPackage(basePackages = WebUiAutoConfiguration.BASE_PACKAGE)
@AutoConfigureBefore(DataAutoConfiguration.class)
@ConditionalOnClass(UiMenu.class)
@Configuration(proxyBeanMethods = false)
public class WebUiAutoConfiguration extends IcepointFrameworkBaseConfiguration {

    public static final String BASE_PACKAGE = "com.icepoint.framework.web.ui";

    @Override
    public void configure(PackageScanRegistry registry) {
        registry.addBasePackages(BASE_PACKAGE);
    }

}
