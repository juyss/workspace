package com.github.tangyi.msc.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@RefreshScope
@ConditionalOnExpression("!'${rhyx-sms}'.isEmpty()")
@ConfigurationProperties(prefix = "rhyx-sms")
public class RhyxSmsProperties {

    /**
     * cpcode
     */
    private String cpcode;

    /**
     * key
     */
    private String key;

    /**
     * 融合云信平台发送短信接口url
     */
    private String sendApiUrl;

    /**
     * 模板id  template开头的三个数组参数，角标要相对应
     */
    private String[] templateids;
    /**
     * 模板内容  template开头的三个数组参数，角标要相对应
     */
    private String[] templates;
    /**
     * 模板名称  template开头的三个数组参数，角标要相对应
     */
    private String[] templateNames;

}
