package com.github.tangyi.webcast.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 直播运营商相关配置
 *
 * @author gaokx
 * @date 2019/6/22 13:31
 */
@Data
@Configuration
@RefreshScope
@ConditionalOnExpression("!'${webcast}'.isEmpty()")
@ConfigurationProperties(prefix = "webcast")
@ToString
public class WebcastProperties {

  /**
   * userId
   */
  private String userId;

  /**
   * appId
   */
  private String appId;

  /**
   * appSecret
   */
  private String appSecret;

}
