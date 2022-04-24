package com.icepoint.framework.workorder.config.agilebpm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jiawei Zhao
 */
@Data
@ConfigurationProperties(prefix = "agile-bpm")
public class AgileBpmProperties {

    private String protocol = "http";

    private String host = "127.0.0.1";

    private int port;

    private String contextPath = "/";

    private String database;

}
