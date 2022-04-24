package com.github.tangyi.exam.api.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 考生考试关联表
 *
 * @author gaokx
 * @date 2018/11/8 20:43
 */
@Data
public class ExaminationUserRelation extends BaseEntity<ExaminationUserRelation> {

    @NotBlank(message = "考试ID不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationId;

    /**
     * 考生ID
     */
    @NotBlank(message = "用户ID不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

}
