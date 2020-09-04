package com.juyss.config;

import com.juyss.pojo.Admin;
import com.juyss.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SpringConfig
 * @Desc:
 * @package com.juyss
 * @project KuangStudy-Spring
 * @date 2020/9/1 21:13
 */
@Configuration
@ComponentScan("com.juyss.pojo")
public class SpringConfig {

    @Bean
    public User getUser(){
        return new User();
    }

    @Bean
    public Admin getAdmin(){
        System.out.println("@Bean");
        return new Admin();
    }

}
