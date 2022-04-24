package com.github.tangyi.tools.dto.resp;

import com.github.tangyi.tools.utils.wx.WxMpGsonBuilder;
import com.github.tangyi.tools.utils.wx.enums.EnumWxMpStatus;
import lombok.Data;

/**
 * 微信模板消息响应信息
 *
 * @author gaokx
 */
@Data
public class WxMpTemplateMessageResp {

    private Integer errcode;

    private String errmsg;

    private Long msgid;

    public static WxMpTemplateMessageResp fromJson(String json) {
        WxMpTemplateMessageResp templateMessageResp = WxMpGsonBuilder
            .create().fromJson(json, WxMpTemplateMessageResp.class);
        if (templateMessageResp.getErrcode() != null) {
            EnumWxMpStatus wxMpStatus = EnumWxMpStatus.getByCode(templateMessageResp.getErrcode());
            if (wxMpStatus != null) {
                templateMessageResp.setErrmsg(wxMpStatus.getLabel());
            }
        }
        return templateMessageResp;
    }
}
