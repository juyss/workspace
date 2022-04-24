package com.github.tangyi.webcast.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v1/webcast/channel/urlMarquee")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}