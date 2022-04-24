package com.github.tangyi.exam.api.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 选择题的选项
 *
 * @author gaokx
 * @date 2020/11/3 20:53
 */
@Data
public class SubjectOption extends BaseEntity<SubjectOption> {

    /**
     * 选择题ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "选择题ID")
    private Long subjectChoicesId;

    /**
     * 选项名称
     */
    @ApiModelProperty(value = "选项名称")
    private String optionName;

    /**
     * 选项内容
     */
    @ApiModelProperty(value = "选项内容")
    private String optionContent;

    /**
     * 是否正确
     */
    @ApiModelProperty(value = "是否正确")
    private String right;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort ;

}
