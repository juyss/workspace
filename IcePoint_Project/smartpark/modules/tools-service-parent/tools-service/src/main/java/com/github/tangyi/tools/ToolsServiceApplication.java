package com.github.tangyi.tools;


import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.utils.Assert;
import com.github.tangyi.tools.properties.WxAccessTicketProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author gaokx
 * @Description
 * @create 2021-03-22 23:52
 **/
@SpringBootApplication(scanBasePackages = {CommonConstant.BASE_PACKAGE})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {CommonConstant.BASE_PACKAGE})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCircuitBreaker
@MapperScan(basePackages = {"com.github.tangyi.mapper"})
public class ToolsServiceApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(ToolsServiceApplication.class, args);
  }

  @Autowired
  WxAccessTicketProperties wxAccessTicketProperties;


  @Override
  public void run(String... args) {
    Assert.notNull(wxAccessTicketProperties,"wx开发者配置为空");
    Assert.notNull(wxAccessTicketProperties.getAppId(),"wx开发者配置appId为空");
    Assert.notNull(wxAccessTicketProperties.getAppSecret(),"wx开发者配置appSecret为空");
  }
}
