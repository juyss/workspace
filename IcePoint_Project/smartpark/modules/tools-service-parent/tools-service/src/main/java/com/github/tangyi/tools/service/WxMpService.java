package com.github.tangyi.tools.service;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import com.github.tangyi.tools.Constants;
import com.github.tangyi.tools.dto.req.WxMpTemplateMessageReq;
import com.github.tangyi.tools.dto.resp.WxMpTemplateMessageResp;
import com.github.tangyi.tools.utils.wx.enums.EnumWxMpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gaokx
 * @date 2021/03/23 13:23
 */
@Slf4j
@Service
public class WxMpService {
    @Autowired
    WxAccessTokenService wxAccessTokenService ;

    /**
     * 发送模板消息
     * @param templateMessageReq
     * @return
     */
    public WxMpTemplateMessageResp sendTemplateMessage(WxMpTemplateMessageReq templateMessageReq) {
        WxMpTemplateMessageResp templateMessageResp = sendRetryTemplateMessage(templateMessageReq.getAccessToken(), templateMessageReq, 3);
        return templateMessageResp;
    }

    /**
     * 发送微信模板消息
     * @param accessToken token
     * @param templateMessageReq 请求参数
     * @param retryTimes 重试次数
     * @return
     */
    private WxMpTemplateMessageResp sendRetryTemplateMessage(String accessToken, WxMpTemplateMessageReq templateMessageReq, int retryTimes) {
        log.info("正在发送模板消息， accessToken：{}，templateMessageReq：{}",  accessToken, templateMessageReq.toJson());
        WxMpTemplateMessageResp templateMessageResp = null;
        for (int i = 0; i < retryTimes; i++) {
            log.debug("正在尝试第{}次发送模板消息", i + 1);
            String response = HttpUtil.post(Constants.TEMPLATE_MESSAGE_SEND_URL + accessToken, templateMessageReq.toJson());
            log.debug("响应结果为：{}", response);
            templateMessageResp = WxMpTemplateMessageResp.fromJson(response);
            if (templateMessageResp != null && EnumWxMpStatus.CODE_0.getCode().equals(templateMessageResp.getErrcode())) {
                log.info("推送微信模板消息成功，结果为{}", JSONUtil.toJsonStr(templateMessageResp));
                return templateMessageResp;
            }
        }
          log.warn("推送微信模板消息失败，结果为{}", JSONUtil.toJsonStr(templateMessageResp));
        return templateMessageResp;
    }

}


