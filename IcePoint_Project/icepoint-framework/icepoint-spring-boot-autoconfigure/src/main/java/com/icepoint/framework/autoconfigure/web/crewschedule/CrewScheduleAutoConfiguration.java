package com.icepoint.framework.autoconfigure.web.crewschedule;

import com.icepoint.framework.autoconfigure.IcepointFrameworkBaseConfiguration;
import com.icepoint.framework.autoconfigure.PackageScanRegistry;
import com.icepoint.framework.autoconfigure.data.DataAutoConfiguration;
import com.icepoint.framework.icepoint.web.crewschedule.controller.ToolGroupController;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jiawei Zhao
 */

@ComponentScan(CrewScheduleAutoConfiguration.BASE_PACKAGE)
@AutoConfigurationPackage(basePackages = CrewScheduleAutoConfiguration.BASE_PACKAGE)
@AutoConfigureBefore(DataAutoConfiguration.class)
@ConditionalOnClass(ToolGroupController.class)
@Configuration(proxyBeanMethods = false)
public class CrewScheduleAutoConfiguration extends IcepointFrameworkBaseConfiguration {

    public static final String BASE_PACKAGE = "com.icepoint.framework.icepoint.web.crewschedule";

    @Override
    public void configure(PackageScanRegistry registry) {
        registry.addBasePackages(BASE_PACKAGE);
    }

}
