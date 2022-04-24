package com.icepoint.base.kafka;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource({"classpath:dms.sdk.producer.properties"})
public class ProducerProperties {

    @Value("${enterprise.topic}")
    String enterpriseTopic;
}
