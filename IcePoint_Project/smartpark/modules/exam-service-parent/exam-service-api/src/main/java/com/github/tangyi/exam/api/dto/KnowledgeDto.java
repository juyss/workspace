package com.github.tangyi.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author tangyi
 * @date 2019/1/1 22:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class KnowledgeDto extends BaseEntity<KnowledgeDto> {

    /**
     * 知识名称
     */
    private String knowledgeName;

    /**
     * 知识描述
     */
    private String knowledgeDesc;

    /**
     * 附件ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long attachmentId;

    /**
     * 状态
     */
    private String status;

    /**
     * 附件名称
     */
    private String attachName;

    /**
     * 附件大小
     */
    private String attachSize;
}
