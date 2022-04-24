package com.icepoint.framework.workorder.configuration.agilebpm.sys;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(
        prefix = "ab.simple-mq.redis-consumer"
)
public class AbRedisMessageQueueConsumerProperties {

    private String redisTemplateBeanName = "redisTemplate";

    private int handleMessageCoreThreadSize = 1;

    private int handleMessageMaxThreadSize = 2;

    private long handleMessageKeepAliveTime = 30L;

    private long listenInterval = 5000L;

}