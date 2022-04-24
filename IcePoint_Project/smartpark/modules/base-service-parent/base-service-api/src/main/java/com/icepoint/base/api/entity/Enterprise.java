package com.icepoint.base.api.entity;

import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "ent_enterprise_base")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Enterprise extends BasicEntity<Long> {

    private String companyName;
    private String companyShortName;
    private String creditCode;
    private String creditStartDate;
    private String creditExpiryDate;
    private String companyCategory;
    private String corporateScale;
    private String business;
    private String x;
    private String y;
    private String companyRegisterAddress;
    private String companyAdress;
    private String mailBox;
    private String officialAddress;
    private String operatingStatus;
    private String foundDate;
    private String industry;
    private String mainProducts;
    private String corporateDelegate;
    private String delegatePhone;
    private String telephone;
    private String leaderName;
    private String leaderTel;
    private String companyPeople;
    private String amapX;
    private String amapY;
    private String orderNo;

}