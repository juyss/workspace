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
public class StudyOverRes {

    @ApiModelProperty(value = "完成学习操作是否成功")
    private boolean success;

    @ApiModelProperty(value = "说明信息")
    private String msg;


}
