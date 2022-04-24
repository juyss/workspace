package com.icepoint.framework.workorder.configuration.agilebpm.sys;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(
        prefix = "ab.simple-mq"
)
public class AbSimpleMessageQueueProperties {

    private MessageQueueType messageQueueType= MessageQueueType.SYNCHRONOUS;

}