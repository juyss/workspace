package com.github.tangyi.exam.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyStartRes {

    @ApiModelProperty(value = "是否开始成功，true 表示成功")
    private Boolean success;

    @ApiModelProperty(value = "开始学习后，间隔多少毫秒调用一次doing api")
    private Long duration;

    @ApiModelProperty(value = "课程时长（秒）")
    private Integer courseTime;

    @ApiModelProperty(value = "已学习的时长（秒）")
    private Integer doneTime;
}
