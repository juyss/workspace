package com.github.tangyi.webcast.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 直播频道-回放视频信息表
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChannelVideo extends BaseEntity<ChannelVideo> {

    private String channelName;//频道名称
    private String channelId;//频道ID
    private String channelSessionId;//场次ID
    private String fileId;//视频ID，文件ID
    private String fileName;//视频名称，文件名称
    private String fileSize;//视频大小，文件大小（单位：字节）
    private String resolution;//分辨率
    private String url;//下载地址
    private Integer bitrate;//码率（单位：字节）
    private Integer duration;//视频时长（单位：秒）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;//开始录制时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;//结束录制时间
    private String obsUploaded;//是否已上传到OBS
    private Integer deleted;//是否删除
    private String obsUrl;//OBS的下载地址
    private String verificationCode;//验证码
    private String type;//验证类型
    private List<ChannelWhiteList> channelWhiteListList;//白名单

}
