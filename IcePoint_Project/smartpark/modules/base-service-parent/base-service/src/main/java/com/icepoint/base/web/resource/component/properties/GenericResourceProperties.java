package com.icepoint.base.web.resource.component.properties;

import com.icepoint.base.config.web.YamlPropertySourceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 *
 */
@RefreshScope
@Data
@Configuration
@PropertySource(value = "classpath:generic-resource.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "generic-table")
public class GenericResourceProperties {

    private List<Entity> entities;

    private ResourceProperties resource = new ResourceProperties();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Entity {

        private String name;

        private String key;
    }
}
