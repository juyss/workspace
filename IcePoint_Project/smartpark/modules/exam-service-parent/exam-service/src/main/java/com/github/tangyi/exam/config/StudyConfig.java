package com.github.tangyi.exam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "study")
@Configuration
public class StudyConfig {
    private Long duration = 5000L;
}
