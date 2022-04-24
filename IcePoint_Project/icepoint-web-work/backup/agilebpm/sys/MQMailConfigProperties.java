package com.icepoint.framework.workorder.configuration.agilebpm.sys;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(
        prefix = "ab.mail"
)
public class MQMailConfigProperties {

    private String sendHost = "";

    private int sendPort = 465;

    private boolean SSL = true;

    private String nickName = "";

    private String mailAddress = "";

    private String password = "";
}