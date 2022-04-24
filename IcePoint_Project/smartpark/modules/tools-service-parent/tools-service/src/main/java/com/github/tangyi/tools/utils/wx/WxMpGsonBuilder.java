package com.github.tangyi.tools.utils.wx;

import com.github.tangyi.tools.dto.req.WxMpTemplateMessageReq;
import com.github.tangyi.tools.dto.resp.WxMpAccessTokenResp;
import com.github.tangyi.tools.dto.resp.WxMpAccessTokenRespGsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WxMpGsonBuilder {

    private static final GsonBuilder INSTANCE = new GsonBuilder();

    /**
     * 初始化wx json解析适配器
     */
    static {
        INSTANCE.disableHtmlEscaping();
        INSTANCE.registerTypeAdapter(WxMpAccessTokenResp.class, new WxMpAccessTokenRespGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpAccessTokenResp.class, new WxMpAccessTokenRespGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpTemplateMessageReq.class, new WxMpTemplateMessageReqGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpAccessTokenResp.class,new WxMpAccessTokenRespGsonAdapter());
    }

    public static Gson create() {
        return INSTANCE.create();
    }

}
