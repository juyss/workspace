package com.github.tangyi.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.tangyi.common.core.persistence.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import lombok.NoArgsConstructor;

/**
 * 考试记录DTO
 *
 * @author tangyi
 * @date 2018-12-26 16:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ExaminationRecordDto extends BaseEntity<ExaminationRecordDto> {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationId;

    @ApiModelProperty(value = "考试名称")
    private String examinationName;

    @ApiModelProperty(value = "考试类型")
    private Integer type;

    private String attention;

    private Date currentTime;

    @ApiModelProperty(value = "考试开始时间")
    private Date startTime;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "难度等级(0:简单；1：一般；2：略难；3：非常难)")
    private String level;


    @ApiModelProperty(value = "考试结束时间")
    private Date endTime;


    @ApiModelProperty(value = "考试持续时间")
    private String duration;

    /**
     * 总分
     */
    @ApiModelProperty(value = "总分")
    private Integer totalScore;

    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Double score;


    @ApiModelProperty(value = "平均分")
    private Double avgScore;


    @ApiModelProperty(value = "考试状态")
    private Integer status;

    /**
     * 封面
     */
    private String avatar;

    /**
     * 学院
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long collegeId;

    /**
     * 专业
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long majorId;

    /**
     * 课程
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long courseId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 考生名称
     */
    private String userName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 考试时间
     */
    private String examTime;

    /**
     * 错误题目数量
     */
    private Integer inCorrectNumber;

    /**
     * 正确题目数量
     */
    private Integer correctNumber;

    /**
     * 提交状态 1-已提交 0-未提交
     */
    private Integer submitStatus;

    private String submitStatusName;

      /**
       * 答题列表
       */
      private List<AnswerDto> answers;

}
