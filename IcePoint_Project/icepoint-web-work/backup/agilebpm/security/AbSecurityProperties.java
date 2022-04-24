package com.icepoint.framework.workorder.configuration.agilebpm.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(
        prefix = "ab.security"
)
public class AbSecurityProperties {

    private String xssIgnores = "";

    private String csrfIgnores = "127.0.0.1";

    private String authIgnores = "/login.*";

}