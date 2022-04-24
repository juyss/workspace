package com.icepoint.framework.workorder.config.agilebpm;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@EnableConfigurationProperties(AgileBpmProperties.class)
@Configuration
public class AgileBpmAutoConfiguration {

    public static final String REST_TEMPLATE = "agileBpmRestTemplate";

    private final AgileBpmProperties properties;

    @Bean(REST_TEMPLATE)
    public RestTemplate agileBpmRestTemplate() {

        String protocol = properties.getProtocol();
        String host = properties.getHost();
        int port = properties.getPort();
        String contextPath = properties.getContextPath();
        if ("/".equals(contextPath)) {
            contextPath = "";
        } else if (!StringUtils.startsWithIgnoreCase(contextPath, "/")) {
            contextPath = "/" + contextPath; // NOSONAR
        }

        return new RestTemplateBuilder()
                .rootUri(String.format("%s://%s:%s%s", protocol, host, port, contextPath))
                .build();
    }

}
