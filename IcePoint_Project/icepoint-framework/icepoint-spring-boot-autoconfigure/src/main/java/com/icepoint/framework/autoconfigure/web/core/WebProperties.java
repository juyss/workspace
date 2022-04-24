package com.icepoint.framework.autoconfigure.web.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jiawei Zhao
 */
@Data
@ConfigurationProperties(prefix = "icepoint.web")
public class WebProperties {
}
