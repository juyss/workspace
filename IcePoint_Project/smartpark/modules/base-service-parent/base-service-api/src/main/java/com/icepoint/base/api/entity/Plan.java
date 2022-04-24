package com.icepoint.base.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "pk_plan")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Plan extends BasicEntity<Long> {

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