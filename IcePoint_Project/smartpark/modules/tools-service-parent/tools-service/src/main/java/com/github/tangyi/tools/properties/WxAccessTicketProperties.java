package com.github.tangyi.tools.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author gaokx
 * @Description
 * @create 2021-03-23 15:55
 **/
@Data
@Configuration
@RefreshScope
public class WxAccessTicketProperties {
  @Value("${wx_appid}")
  private String appId;
  @Value("${wx_appsecret}")
  private String appSecret;
}
