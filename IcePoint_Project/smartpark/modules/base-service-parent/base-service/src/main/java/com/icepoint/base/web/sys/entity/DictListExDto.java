package com.icepoint.base.web.sys.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DictListExDto {

    /**
     * 创建人
     */
    @NotEmpty(message = "请输入创建人")
    private long custId;

    /**
     * 英文类名
     */
    @NotEmpty(message = "请输入英文类名")
    private String categoryEn;

    /**
     * 是否删除
     */
    @NotEmpty(message = "请输入是否删除")
    private int deleted;

    /**
     * 所有者编号
     */
    @NotEmpty(message = "请输入所有者编号")
    private long ownerId;

    /**
     * 系统编号
     */
    @NotEmpty(message = "请输入系统编号")
    private long appId;

}