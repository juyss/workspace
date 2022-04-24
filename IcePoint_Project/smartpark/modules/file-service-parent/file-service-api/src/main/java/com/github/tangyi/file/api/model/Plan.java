package com.github.tangyi.file.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Plan extends BaseEntity<Plan> {

    private String docNo;//

    private String fileName;//

    private String regulator;//

    private String source;//

    private String approver;//

    private String intelligence;//

    private String fileType;//

    private String planMap;//

    private String realityMap;//

    private String environprocess;//

    private String ownerId;//

    private String appId;//

    private String annex;//

    private String annexName;//

    private String planMapName;//

    private String realityMapName;//

    private String deptId;//

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;//

    private Long createUser;//

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;//

    private Long updateUser;//

    private Integer deleted;//

}