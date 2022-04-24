package com.icepoint.framework.workorder.configuration.agilebpm.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(
        prefix = "agile-bpm.id-generator"
)
public class IdGeneratorProperties {

    private long machine = 1L;

    private byte machineBits = 3;

    private byte sequenceBits = 15;

    private byte timeSequence = 45;
}