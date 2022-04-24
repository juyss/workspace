package com.icepoint.framework.workorder.configuration.agilebpm.sys;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(
        prefix = "ab.redis"
)
public class RedisConfigProperties {

    private Boolean useRedisCache = Boolean.FALSE;

}