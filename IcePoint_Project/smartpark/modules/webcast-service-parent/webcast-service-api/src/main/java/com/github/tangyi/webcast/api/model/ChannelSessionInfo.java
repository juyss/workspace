package com.github.tangyi.webcast.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.NonLeaked;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@ApiModel(value = "channel_session_info", description = "场次详情")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChannelSessionInfo implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "频道id")
    private String channelId;

    @ApiModelProperty(value = "场次id")
    private String sessionId;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "视频上传状态 N:未上传 O:上传中 Y:上传完成")
    private String uploadStatus;
}
