package com.github.tangyi.exam.api.dto;

import com.github.tangyi.exam.api.module.Course;
import com.github.tangyi.exam.api.module.Examination;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tangyi
 * @date 2018/11/20 22:02
 */
@Data
@NoArgsConstructor
public class ExaminationDto extends Examination {

    private Course course;

    /**
     * 封面地址
     */
    private String logoUrl;

    /**
     * 已命制试题总分
     */
    @ApiModelProperty(value = "已命制试题总分")
    private Double itemScoreTotal;

    @ApiModelProperty(value = "考试状态0:未开始 1：进行中 2：已结束")
    private Integer examinationStatus ;

}
