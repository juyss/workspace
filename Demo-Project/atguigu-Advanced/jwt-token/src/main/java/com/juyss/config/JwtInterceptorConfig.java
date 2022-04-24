package com.juyss.config;

import com.juyss.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: JwtInterceptorConfig
 * @Desc: 拦截器配置类
 * @package com.juyss.config
 * @project atguigu-Advanced
 * @date 2020/12/6 20:59
 */
@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor()) //注册自定义拦截器
                .addPathPatterns("/**") //拦截所有路径
                .excludePathPatterns("/login"); //排除登陆请求
    }
}