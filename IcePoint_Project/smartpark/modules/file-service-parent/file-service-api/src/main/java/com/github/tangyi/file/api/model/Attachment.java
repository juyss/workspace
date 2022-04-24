package com.github.tangyi.file.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tangyi.common.core.persistence.BaseEntity;
import com.github.tangyi.file.api.constant.AttachmentConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 附件信息
 *
 * @author gaokx
 * @date 2020/11/30 13:07
 */
@Data
@ToString
@ApiModel(value = "Attachment", description = "附件信息")
public class Attachment extends BaseEntity<Attachment> {

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String attachName;

    /**
     * 附件大小
     */
    @ApiModelProperty(value = "附件大小--上传后后端获取")
    private String attachSize;

    /**
     * 附件类型
     */
    @ApiModelProperty(value = "附件类型--上传后后端获取")
    private String attachType;

    /**
     * 组名称
     */
    @ApiModelProperty(value = "组名称")
    private String groupName;

    /**
     * 文件ID
     */
    @ApiModelProperty(value = "文件ID--上传后后端获取")
    @JsonIgnore
    private String fastFileId;

    /**
     * 业务流水号
     */
    @ApiModelProperty(value = "业务流水号")
    private String busiId;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型--0:普通附件,1：用户头像,2:知识库附件 其他待补充")
    private String busiType = AttachmentConstant.BUSI_TYPE_NORMAL_ATTACHMENT;

    /**
     * 业务模块
     */
    @ApiModelProperty(value = "业务模块或分类")
    private String busiModule;

    /**
     * 预览地址
     */
    @ApiModelProperty(value = "PDF预览地址--保存后，后台存入拼接")
    private String previewUrl;

    /**
     * 预览地址
     */
    @ApiModelProperty(value = "原预览地址--保存后，后台存入拼接")
    private String previewUrlSource;

    /**
     * 上传类型，1：本地目录，2：fastDfs，3：七牛云
     */
    @ApiModelProperty(value = "上传类型，1：本地目录，2：fastDfs，3：七牛云 4: 华为obs")
    private Integer uploadType;

    /**
     * 上传结果
     */
    @ApiModelProperty(value = "上传结果")
    private String uploadResult;
}
