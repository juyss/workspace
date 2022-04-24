package com.icepoint.framework.autoconfigure.web.core;

import com.icepoint.framework.autoconfigure.IcepointFrameworkBaseConfiguration;
import com.icepoint.framework.autoconfigure.PackageScanRegistry;
import com.icepoint.framework.autoconfigure.data.DataAutoConfiguration;
import com.icepoint.framework.core.util.EnvironmentUtils;
import com.icepoint.framework.web.core.support.StringToTimeRangeConverter;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpHeaders;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Mvc 配置
 *
 * @author Jiawei Zhao
 */
@EnableConfigurationProperties(WebProperties.class)
@EnableAsync
@EnableCaching
@EnableWebMvc
@ComponentScan(WebCoreAutoConfiguration.BASE_PACKAGE)
@AutoConfigurationPackage(basePackages = WebCoreAutoConfiguration.BASE_PACKAGE)
@ConditionalOnClass(WebMvcAutoConfiguration.class)
@AutoConfigureBefore(DataAutoConfiguration.class)
@Import(JacksonAutoConfiguration.class)
@Configuration
public class WebCoreAutoConfiguration extends IcepointFrameworkBaseConfiguration
        implements WebMvcConfigurer, EnvironmentAware {

    public static final String BASE_PACKAGE = "com.icepoint.framework.web.core";

    private Environment environment;

    @Override
    public void configure(PackageScanRegistry registry) {
        registry.addBasePackages(BASE_PACKAGE);
    }

    @Order(5)
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // TimeRange转换器
        registry.addConverter(new StringToTimeRangeConverter());
    }

    @Profile({"test", "dev"})
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (!EnvironmentUtils.isPro(environment)) {
            registry.addMapping("/**").
                    allowedOriginPatterns("*"). //允许跨域的域名，可以用*表示允许任何域名使用
                    allowedMethods("*"). //允许任何方法（post、get等）
                    allowedHeaders("*"). //允许任何请求头
                    allowCredentials(true). //带上cookie信息
                    exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L); //maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
