package com.github.tangyi.file.api.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InstitutionHistory extends BaseEntity<InstitutionHistory> {

    private Long dataId;//

    private String name;//

    private String type;//

    private String fileType;//

    private String auditStatus;//

    private String annex;//

    private String annexName;//

    private String fileName;//

    private Integer version;//

    private Integer deleted;//
}