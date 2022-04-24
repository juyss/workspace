package com.github.tangyi.tools.dto.req;


import com.github.tangyi.tools.utils.wx.WxMpGsonBuilder;

/**
 * 微信access_token获取请求
 *
 * @author Trency
 */
public class WxMpAccessTokenReq {

    /**
     * 获取access_token填写client_credential
     */
    private String grantType = "client_credential";

    /**
     * appId
     */
    private String appid;

    /**
     * secret
     */
    private String secret;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String toJson() {
        return WxMpGsonBuilder.create().toJson(this);
    }
}
