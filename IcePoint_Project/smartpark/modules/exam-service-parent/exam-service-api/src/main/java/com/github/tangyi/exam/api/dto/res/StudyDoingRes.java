package com.github.tangyi.exam.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
public class StudyDoingRes {

    @ApiModelProperty(value = "此次学时提交是否有效")
    private boolean isRational;

    @ApiModelProperty(value = "是否完成了课程学时")
    private boolean isOver;

    @ApiModelProperty(value = "课程时长（秒）")
    private Integer courseTime;

    @ApiModelProperty(value = "已学习的时长（秒）")
    private Integer doneTime;

    public StudyDoingRes(boolean isRational, boolean isOver,Integer courseTime,Integer doneTime) {
        this.isRational = isRational;
        this.isOver = isOver;
        this.courseTime = courseTime;
        this.doneTime = doneTime;
    }
}
