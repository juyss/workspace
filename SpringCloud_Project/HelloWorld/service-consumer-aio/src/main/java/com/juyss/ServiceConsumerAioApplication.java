package com.juyss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceConsumerAioApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerAioApplication.class, args);
    }

}
