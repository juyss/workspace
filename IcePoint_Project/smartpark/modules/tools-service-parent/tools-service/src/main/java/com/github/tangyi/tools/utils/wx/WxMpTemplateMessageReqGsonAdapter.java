package com.github.tangyi.tools.utils.wx;

import com.github.tangyi.tools.dto.req.WxMpTemplateMessageReq;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class WxMpTemplateMessageReqGsonAdapter implements JsonSerializer<WxMpTemplateMessageReq> {

    @Override
    public JsonElement serialize(WxMpTemplateMessageReq message, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject messageJson = new JsonObject();
        messageJson.addProperty("touser", message.getToUser());
        messageJson.addProperty("template_id", message.getTemplateId());
        if (message.getUrl() != null) {
            messageJson.addProperty("url", message.getUrl());
        }

        if (message.getMiniProgram() != null) {
            JsonObject miniProgramJson = new JsonObject();
            miniProgramJson.addProperty("appid", message.getMiniProgram().getAppid());
            miniProgramJson.addProperty("pagepath", message.getMiniProgram().getPagePath());
            messageJson.add("miniprogram", miniProgramJson);
        }

        JsonObject data = new JsonObject();
        messageJson.add("data", data);

        for (WxMpTemplateMessageReq.WxMpTemplateData templateData : message.getData()) {
            JsonObject dataJson = new JsonObject();
            dataJson.addProperty("value", templateData.getValue());
            if (templateData.getColor() != null) {
                dataJson.addProperty("color", templateData.getColor());
            }
            data.add(templateData.getName(), dataJson);
        }

        return messageJson;
    }

}
