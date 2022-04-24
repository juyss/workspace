package com.github.tangyi.tools.dto.resp;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class WxMpAccessTokenRespGsonAdapter implements JsonDeserializer<WxMpAccessTokenResp> {

    @Override
    public WxMpAccessTokenResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        WxMpAccessTokenResp accessTokenResp = new WxMpAccessTokenResp();
        JsonObject jsonObject = json.getAsJsonObject();

        JsonElement accessToken = jsonObject.get("access_token");
        if (accessToken != null && !accessToken.isJsonNull()) {
            accessTokenResp.setAccessToken(accessToken.getAsString());
        }

        JsonElement expiresIn = jsonObject.get("expires_in");
        if (expiresIn != null && !expiresIn.isJsonNull()) {
            accessTokenResp.setExpiresIn(expiresIn.getAsInt());
        }

        JsonElement errcode = jsonObject.get("errcode");
        if (errcode != null && !errcode.isJsonNull()) {
            accessTokenResp.setErrcode(errcode.getAsInt());
        }

        JsonElement errmsg = jsonObject.get("errmsg");
        if (errmsg != null && !errmsg.isJsonNull()) {
            accessTokenResp.setErrmsg(errmsg.getAsString());
        }

        return accessTokenResp;
    }

}
