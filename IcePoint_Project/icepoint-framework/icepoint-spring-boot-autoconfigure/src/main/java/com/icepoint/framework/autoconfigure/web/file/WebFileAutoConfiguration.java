package com.icepoint.framework.autoconfigure.web.file;

import com.icepoint.framework.autoconfigure.IcepointFrameworkBaseConfiguration;
import com.icepoint.framework.autoconfigure.PackageScanRegistry;
import com.icepoint.framework.autoconfigure.data.DataAutoConfiguration;
import com.icepoint.framework.web.file.io.FileManager;
import com.icepoint.framework.web.file.io.LocalStorageFileManager;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Jiawei Zhao
 */
@Import(FileManagerAutoConfiguration.class)
@EnableConfigurationProperties(WebFileProperties.class)
@ComponentScan(WebFileAutoConfiguration.BASE_PACKAGE)
@AutoConfigurationPackage(basePackages = WebFileAutoConfiguration.BASE_PACKAGE)
@AutoConfigureBefore(DataAutoConfiguration.class)
@ConditionalOnClass(FileManager.class)
@Configuration
public class WebFileAutoConfiguration extends IcepointFrameworkBaseConfiguration {

    public static final String BASE_PACKAGE = "com.icepoint.framework.web.file";

    @Override
    public void configure(PackageScanRegistry registry) {
        registry.addBasePackages(BASE_PACKAGE);
    }

    @ConditionalOnMissingBean
    @Bean
    public FileManager fileManager() {
        return new LocalStorageFileManager();
    }
}
