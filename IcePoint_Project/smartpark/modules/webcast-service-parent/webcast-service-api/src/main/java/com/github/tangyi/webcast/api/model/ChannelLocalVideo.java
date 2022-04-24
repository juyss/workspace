package com.github.tangyi.webcast.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "webcast_local_video", description = "本地视频")
public class ChannelLocalVideo {
    @ApiModelProperty(value = "频道名称")
    private String channelName;//频道名称
    @ApiModelProperty(value = "频道ID")
    private String channelId;//频道ID
    @ApiModelProperty(value = "场次ID")
    private String channelSessionId;//场次ID
    @ApiModelProperty(value = "视频ID，文件ID")
    private String fileId;//视频ID，文件ID
    @ApiModelProperty(value = "视频名称，文件名称")
    private String fileName;//视频名称，文件名称
    @ApiModelProperty(value = "视频大小，文件大小（单位：字节）")
    private String fileSize;//视频大小，文件大小（单位：字节）
    @ApiModelProperty(value = "分辨率")
    private String resolution;//分辨率
    @ApiModelProperty(value = "下载地址")
    private String url;//下载地址
    @ApiModelProperty(value = "码率（单位：字节）")
    private Integer bitrate;//码率（单位：字节）
    @ApiModelProperty(value = "视频时长（单位：秒）")
    private Integer duration;//视频时长（单位：秒）
    @ApiModelProperty(value = "开始录制时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;//开始录制时间
    @ApiModelProperty(value = "结束录制时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;//结束录制时间
    @ApiModelProperty(value = "是否已上传到OBS")
    private String obsUploaded;//是否已上传到OBS
    @ApiModelProperty(value = "OBS的下载地址")
    private String obsUrl;//OBS的下载地址
    @ApiModelProperty(value = "验证码")
    private String verificationCode;//验证码
    @ApiModelProperty(value = "验证类型")
    private String type;//验证类型
    @ApiModelProperty(value = "白名单")
    private List<ChannelWhiteList> channelWhiteListList;//白名单
}
