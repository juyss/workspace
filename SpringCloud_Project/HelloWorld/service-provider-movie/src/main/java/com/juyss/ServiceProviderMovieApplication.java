package com.juyss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProviderMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderMovieApplication.class, args);
    }

}
