package com.github.tangyi.webcast;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.webcast.properties.WebcastProperties;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.config.LiveGlobalConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 直播客户端 Application
 * @author gaokx
 * @date 2021/1/6 17:09
 */
@SpringBootApplication(scanBasePackages = {CommonConstant.BASE_PACKAGE})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {CommonConstant.BASE_PACKAGE})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCircuitBreaker
@EnableScheduling
@MapperScan(basePackages = {"com.github.tangyi.mapper"})
@Slf4j
public class WebcastServiceApplication implements CommandLineRunner {

  @Autowired
  WebcastProperties webcastProperties;

  public static void main(String[] args) {
    SpringApplication.run(WebcastServiceApplication.class, args);
  }

  @Override
  public void run(String... args) {
    LiveGlobalConfig.init(webcastProperties.getAppId(), webcastProperties.getUserId(),
        webcastProperties.getAppSecret());
    log.info("--初始化直播供应商配置信息完成--: {}",webcastProperties);
  }

}
