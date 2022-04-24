package com.github.tangyi.msc.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tangyi
 * @date 2019/6/22 13:07
 */
@Data
@ApiModel(value = "SmsDto", description = "发送短信请求DTO")
public class SmsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接收人
     */
    @ApiModelProperty(value = "手机号", dataType = "String", example = "13523426666")
    private String receiver;

    /**
     * 发送内容
     */
    @ApiModelProperty(value = "发送内容", dataType = "String", example = "您已完成的在线学习平台注册，账号：13522225555，密码：123456")
    private String content;
}
