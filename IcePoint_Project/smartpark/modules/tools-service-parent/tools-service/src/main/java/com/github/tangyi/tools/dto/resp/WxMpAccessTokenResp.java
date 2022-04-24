package com.github.tangyi.tools.dto.resp;

import com.github.tangyi.tools.utils.wx.WxMpGsonBuilder;
import com.github.tangyi.tools.utils.wx.enums.EnumWxMpStatus;
import lombok.Data;

/**
 * 微信access_token获取响应
 *
 * @author Trency
 */
@Data
public class WxMpAccessTokenResp extends BaseResp{

    /**
     * 凭证
     */
    private String accessToken;

    /**
     * 有效期，秒，默认7200
     */
    private Integer expiresIn;

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    public static WxMpAccessTokenResp fromJson(String json) {
        WxMpAccessTokenResp accessTokenResp = WxMpGsonBuilder
            .create().fromJson(json, WxMpAccessTokenResp.class);
        if (accessTokenResp.getErrcode() != null) {
            EnumWxMpStatus wxMpStatus = EnumWxMpStatus.getByCode(accessTokenResp.getErrcode());
            if (wxMpStatus != null) {
                accessTokenResp.setErrmsg(wxMpStatus.getLabel());
            }
        }
        return accessTokenResp;
    }

}
