package com.juyss.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BeanConfig
 * @Desc:
 * @package com.juyss.config
 * @project HelloWorld
 * @date 2020/10/29 21:39
 */
@SpringBootConfiguration
public class BeanConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
