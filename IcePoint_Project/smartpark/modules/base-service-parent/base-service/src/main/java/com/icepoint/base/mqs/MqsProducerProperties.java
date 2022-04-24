package com.icepoint.base.mqs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource({"classpath:mqs.properties"})
public class MqsProducerProperties {

    @Value("${servers}")
    public String servers;

    @Value("${appId}")
    public String appId;

    @Value("${appSecret}")
    public String appSecret;

    @Value("${topic}")
    public String topic;

    @Value("${tag}")
    public String tag;

    @Value("${businessId}")
    public String businessId;

}
