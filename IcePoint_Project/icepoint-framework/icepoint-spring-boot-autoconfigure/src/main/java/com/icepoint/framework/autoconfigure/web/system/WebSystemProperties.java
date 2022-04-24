package com.icepoint.framework.autoconfigure.web.system;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jiawei Zhao
 */
@Data
@ConfigurationProperties(prefix = "icepoint.web.system")
public class WebSystemProperties {

    private String pathPrefix = "system";
}
