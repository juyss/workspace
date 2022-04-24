package com.github.tangyi.exam.api.dto;

import com.github.tangyi.common.core.persistence.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考生考试关联表
 *
 * @author gaokx
 * @date 2020/11/5 20:43
 */
@Data
@NoArgsConstructor
public class ExaminationUserRelationDto extends BaseEntity<ExaminationUserRelationDto> {

    @ApiModelProperty(value = "考试ID")
    private Long examinationId;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "考试名字")
    private String name ;
    @ApiModelProperty(value = "手机号")
    private String phone ;
    @ApiModelProperty(value = "用户加入考试的开始时间")
    private Date startTime;
    @ApiModelProperty(value = "用户加入考试的结束时间")
    private Date endTime;
}
