package com.icepoint.framework.autoconfigure.web.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jiawei Zhao
 */
@Data
@ConfigurationProperties("icepoint.web.file")
public class WebFileProperties {

    private String pathPrefix = "file";
}
