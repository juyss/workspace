package com.github.tangyi.user.synchrodata.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xh
 * @Description  主系统的客户端配置 参见 统一身份认证系统对接文档V0.2.pdf
 * @Date 6:30 2020/11/4
 * @Param
 * @return
 **/
@Configuration
@ConfigurationProperties(prefix = "synch.client")
@Data
public class SynchClientProperties {
    /**
     * 授权url
     */
    private String authUrl = "http://121.36.89.139/idaas/auth/oauth2/token";
    /**
     * 客户端模式 获取token 的参数模版
     */
    private String authParam = "?grantType=%s&clientSecret=%s&clientId=%s";
    /**
     * 客户端模式 获取token 的参数模版
     */
    private String refreshTokenParam = "?grantType=refresh_token&refreshToken=%s&clientSecret=%s&clientId=%s";
    /**
     * 客户端id
     */
    private String clientId = "083OAJNKKLS45F7439F8FFABDF09AF98";
    /**
     * 客户端密钥
     */
    private String clientSecret = "I909NUIHUIKDUABB92EF957D78686EC2";
    /**
     * 模式
     */
    private String grantType = "client_credentials";
}
