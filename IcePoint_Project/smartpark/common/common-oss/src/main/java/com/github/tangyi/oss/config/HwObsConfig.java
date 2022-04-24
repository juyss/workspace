package com.github.tangyi.oss.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xh
 * @Description 华为obs云存储配置
 * @Date 14:39 2020/11/20
 * @Param
 * @return
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "hwobs")
@ConditionalOnExpression("!'${hwobs}'.isEmpty()")
public class HwObsConfig {

    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String baseBucketName = "smartpark";
}
