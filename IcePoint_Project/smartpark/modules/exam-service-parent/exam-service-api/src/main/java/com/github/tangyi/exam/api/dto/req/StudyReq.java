package com.github.tangyi.exam.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class StudyReq {
    @ApiModelProperty(value = "任务id",required = true)
    private Long taskId;
    @ApiModelProperty(value = "课程id",required = true)
    private Long courseId;
    @ApiModelProperty(value = "当前时间，毫秒时间戳",required = true)
    private Long currentTime;
}
