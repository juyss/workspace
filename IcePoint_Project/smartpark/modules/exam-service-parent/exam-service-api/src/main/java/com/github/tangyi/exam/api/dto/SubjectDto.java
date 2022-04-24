package com.github.tangyi.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.tangyi.common.core.persistence.BaseEntity;
import com.github.tangyi.exam.api.module.Answer;
import com.github.tangyi.exam.api.module.SubjectOption;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import lombok.NoArgsConstructor;

/**
 * @author tangyi
 * @date 2019/1/9 20:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class SubjectDto extends BaseEntity<SubjectDto> {

    /**
     * 考试ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "考试ID")
    private Long examinationId;

    /**
     * 考试记录ID
     */
    @ApiModelProperty(value = "考试记录ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationRecordId;

    /**
     * 题目分类ID
     */
    @ApiModelProperty(value = "题目分类ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;

    /**
     * 题目分类名称
     */
    @ApiModelProperty(value = "题目分类名称")
    private String categoryName;

    /**
     * 题目名称
     */
    @ApiModelProperty(value = "题目名称")
    private String subjectName;

    /**
     * 题目类型
     */
    @ApiModelProperty(value = "题目类型")
    private Integer type;

    /**
     * 选择题类型
     */
    @ApiModelProperty(value = "选择题类型")
    private Integer choicesType;

    /**
     * 分值
     */
    @ApiModelProperty(value = "score")
    private Double score;


    /**
     * 解析
     */
    @ApiModelProperty(value = "解析")
    private String analysis;

    /**
     * 难度等级
     */
    @ApiModelProperty(value = "难度等级")
    private Integer level;

    /**
     * 答题
     */
    @ApiModelProperty(value = "答题")
    private Answer answer;

    /**
     * 选项列表
     */
    @ApiModelProperty(value = "选项列表")
    private List<SubjectOption> options;
}
