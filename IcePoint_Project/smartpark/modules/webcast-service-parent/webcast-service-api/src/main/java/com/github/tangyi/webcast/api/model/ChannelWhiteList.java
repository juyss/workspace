package com.github.tangyi.webcast.api.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "webcast_channel_whiteList", description = "白名单")
public class ChannelWhiteList {
    /**
     * 白名单id
     */
    @ApiModelProperty(value = "白名单id")
    private Integer id;

    /**
     * 频道id
     */
    @ApiModelProperty(value = "频道id")
    private String fileId;

    /**
     * 会员备注
     */
    @ApiModelProperty(value = "会员备注")
    private String name;

    /**
     * 会员码
     */
    @ApiModelProperty(value = "会员码")
    private String phone;
    /**
     *
     */
    @ApiModelProperty(value = "obsUrl")
    private String obsUrl;
}
