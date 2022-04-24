package com.github.tangyi.pub;

import com.github.tangyi.common.core.constant.CommonConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {CommonConstant.BASE_PACKAGE})
@EnableDiscoveryClient
// 扫描api包里的FeignClient
@EnableFeignClients(basePackages = {CommonConstant.BASE_PACKAGE})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCircuitBreaker
@MapperScan(basePackages = {"com.github.tangyi.mapper",
        "com.github.tangyi.pub.mapper",
        "com.github.tangyi.message.mapper",
        "com.github.tangyi.pub.mapper",
        "com.github.tangyi.office.mapper",
        "com.github.tangyi.introduction.mapper",
        "com.github.tangyi.dataYear.mapper",
        "com.github.tangyi.dataQuarter.mapper"})
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
public class PublicServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicServiceApplication.class, args);
    }

}
