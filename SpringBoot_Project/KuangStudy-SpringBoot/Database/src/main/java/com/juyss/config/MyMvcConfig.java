package com.juyss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MyMvcConfig
 * @Desc:
 * @package com.juyss.config
 * @project KuangStudy-SpringBoot
 * @date 2020/10/24 0:54
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

}
