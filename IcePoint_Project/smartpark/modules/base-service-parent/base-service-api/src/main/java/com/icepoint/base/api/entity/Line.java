package com.icepoint.base.api.entity;

import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "t_tab_line")
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Line extends BasicEntity<Long> {

    @Length(max = 32, message = "单据编号长度不能超过32")
    private String docNo;// 

    private Integer lineNo;// 

    @Length(max = 32, message = "字段名长度不能超过32")
    private String name;//

    @Length(max = 64, message = "字段值长度不能超过64")
    private String value;// 

    private Long ownerId;//

    private Long appId;// 一个应用维护一个商品列表，免去商家各自维护

    private Long createTime;//

    private Long createUser;// 

    private Long updateTime;// 

    private Long updateUser;// 

    private Integer deleted;//

    private Integer docType;//

}