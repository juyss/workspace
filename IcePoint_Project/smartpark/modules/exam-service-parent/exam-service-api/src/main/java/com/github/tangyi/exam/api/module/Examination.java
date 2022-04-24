package com.github.tangyi.exam.api.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.NoArgsConstructor;

/**
 * 考试
 *
 * @author tangyi
 * @date 2018/11/8 20:47
 */
@Data
@NoArgsConstructor
public class Examination extends BaseEntity<Examination> {

    /**
     * 考试名称
     */
    @NotBlank(message = "考试名称不能为空")
    @ApiModelProperty(value = "考试名称")
    private String examinationName;

    /**
     * 考试类型
     */
    @NotNull(message = "考试类型不能为空")
    @ApiModelProperty(value = "考试类型")
    private Integer type;

    /**
     * 难度等级(0:简单；1：一般；2：略难；3：非常难)
     */
    @ApiModelProperty(value = "难度等级(0:简单；1：一般；2：略难；3：非常难)")
    private String level;

    /**
     * 考试注意事项
     */
    @ApiModelProperty(value = "考试注意事项")
    private String attention;

    /**
     * 考试开始时间
     */
    @ApiModelProperty(value = "考试开始时间")
    private Date startTime;

    /**
     * 考试结束时间
     */
    @ApiModelProperty(value = "考试结束时间")
    private Date endTime;

    /**
     * 总分
     */
    @NotNull(message = "总分不能为空")
    @ApiModelProperty(value = "总分不能为空")
    private Integer totalScore;

    /**
     * 考试状态
     */
    private Integer status;

    /**
     * 审核状态 -- 0：未审核  1：已审核
     */
    @ApiModelProperty(value = "审核状态 -- 0：未审核  1：已审核 默认为0 ")
    private Integer auditStatus;

    /**
     * 封面对应的图片id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long avatarId;

    /**
     * 课程
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long courseId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "考试备注介绍等")
    private String remark;

    /**
     * 总分
     */
    @NotNull(message = "及格分不能为空")
    @ApiModelProperty(value = "及格分不能为空")
    private Integer passScore;

}
