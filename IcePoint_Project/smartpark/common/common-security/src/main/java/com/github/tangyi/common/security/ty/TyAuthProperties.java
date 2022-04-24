package com.github.tangyi.common.security.ty;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @Author xh
 * @Description 统一认证的配置
 * @Date 15:49 2020/11/5
 * @Param
 * @return
 **/
@Configuration
@ConfigurationProperties(prefix = "ty.auth")
@Data
public class TyAuthProperties {

    /**
     * 授权url
     */
    private String authorizeUrl = "http://121.36.89.139/idaas/auth/oauth2/authorize";
    /**
     * 用授权码换token 的Url
     */
    private String tokenUrl = "http://121.36.89.139/idaas/auth/oauth2/token";
    /**
     * 登出url
     */
    private String logoutUrl = "http://121.36.89.139/idaas/auth/oauth2/logout";

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
    private String grantType = "authorization_code";
    /**
     * 回调uri 主系统配置的，若要修改，需要联系主系统的工程师（金证公司 的 杨辉）
     */
    private String redirectUri = "http://39.100.11.213:9180/api/auth/v1/authentication/ty/callback";

    /**
     * 获取用户信息
     */
    private String getUserInfoUrl= "http://121.36.89.139/idaas/manage/open-api/auth/user-info";
    /**
     * state 没有值时，默认重定向到子系统的管理后台
     */
    private String defaultRedirect= "http://39.100.11.213:9528/#/";
}
