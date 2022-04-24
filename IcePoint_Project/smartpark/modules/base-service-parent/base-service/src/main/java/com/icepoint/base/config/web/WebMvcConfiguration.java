package com.icepoint.base.config.web;

import com.icepoint.base.web.resource.component.query.QueryParameterArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * Web相关配置
 *
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
//    @Bean
//    @NonNull
//    @Override
//    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
//        return new ExclusiveRequestMappingHandlerMapping();
//    }

    /**
     * 增加对{@link org.springframework.data.domain.Pageable}的参数支持
     *
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
        argumentResolvers.add(new QueryParameterArgumentResolver());
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumIgnoreCaseConverterFactory());
    }

}
