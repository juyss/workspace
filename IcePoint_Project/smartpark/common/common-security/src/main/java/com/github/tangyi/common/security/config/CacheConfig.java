package com.github.tangyi.common.security.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * RbacMapper用到的一个缓存key生成器 配置
 */
@Configuration
public class CacheConfig {

    @Bean
    public KeyGenerator myKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                if( params.length>0 &&  params[0] !=null && params[0] instanceof Set){
                    Set<String> param = (Set<String>)params[0];
                    return String.join(",",param);
                }
                return "";
            }
        };
    }
}
