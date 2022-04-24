package com.github.tangyi.exam.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考试报表
 *
 * @author gaokx
 * @date 2018/11/8 20:43
 */
@Data
@NoArgsConstructor
public class ExaminationReportDto  {
    /**
     * 考试ID
     */
    @ApiModelProperty(value = "考试ID")
    private Long examinationId;
    /**
     * 考试名称
     */
    @ApiModelProperty(value = "考试名称")
    private String examinationName;
    /**
     * 考试时长
     */
    @ApiModelProperty(value = "考试时长 时:分")
    private String timeDiff;
    /**
     * 考试人数
     */
    @ApiModelProperty(value = "考试人数")
    private String count;

    /**
     * 参考人数
     */
    @ApiModelProperty(value = "参考人数")
    private Integer takeCount;
    /**
     * 通过人数
     */
    @ApiModelProperty(value = "通过人数")
    private Integer passNum;
    /**
     * 通过率
     */
    @ApiModelProperty(value = "通过率")
    private double passRate;
    /**
     * 平均分
     */
    @ApiModelProperty(value = "平均分")
    private double avgScore;
    /**
     * 最高分
     */
    @ApiModelProperty(value = "最高分")
    private double maxScore;
    /**
     * 中位数
     */
    @ApiModelProperty(value = "中位数")
    private double median;
    /**
     * 众数
     */
    @ApiModelProperty(value = "众数")
    private String mode;

}
