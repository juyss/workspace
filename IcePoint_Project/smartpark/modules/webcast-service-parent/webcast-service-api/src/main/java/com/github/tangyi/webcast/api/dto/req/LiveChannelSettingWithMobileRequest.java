package com.github.tangyi.webcast.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;

public class LiveChannelSettingWithMobileRequest extends LiveChannelSettingRequest.BasicSetting {

    @ApiModelProperty(name = "mobile", value = "手机号", required = true)
    private java.lang.String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
