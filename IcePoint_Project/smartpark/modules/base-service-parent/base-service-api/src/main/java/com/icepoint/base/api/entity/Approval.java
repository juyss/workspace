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

@Table(name = "ent_approval")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Approval extends BasicEntity<Long> {

    private Long tabId;//

    private Long dataId;//

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateTime;//

    private String operator;//

    private String operate;//

    private String auditOpinion;//

}