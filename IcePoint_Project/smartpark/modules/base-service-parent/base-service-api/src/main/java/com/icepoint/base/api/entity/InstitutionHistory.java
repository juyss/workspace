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

@Table(name = "pk_mgt_institution_history")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InstitutionHistory extends BasicEntity<Long> {

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