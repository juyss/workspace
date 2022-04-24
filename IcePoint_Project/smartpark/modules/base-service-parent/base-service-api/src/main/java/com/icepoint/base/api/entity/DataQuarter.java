package com.icepoint.base.api.entity;

import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "pk_data_quarter")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DataQuarter extends BasicEntity<Long> {

    private Integer year;//

    private Integer quarter;//

    private String salesIncome;//

    private String totalPretaxProfits;//

    private String chemicalIncome;//

    private String chemicalProfit;//

    private String iavEnergyQuota;//

    private String energyEfficiency;//

    private Long ownerId;//

    private Long appId;// 一个应用维护一个商品列表，免去商家各自维护

    private Date createTime;//

    private Long createUser;//

    private Date updateTime;//

    private Long updateUser;//

    private Integer deleted;//
}