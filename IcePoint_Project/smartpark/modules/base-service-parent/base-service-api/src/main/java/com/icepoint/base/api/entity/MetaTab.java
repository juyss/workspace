package com.icepoint.base.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 表元数据
 * @author BD
 *
 */
@Table(name = "t_meta_tab")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaTab extends BasicEntity<Long> {

    private Long resId;

    private String tabType;

    @Length(max = 100, message = "编号长度不能超过100")
    private String tabCode;//

    @NotEmpty(message = "名称不能为空")
    @Length(max = 100, message = "名称长度不能超过100")
    private String name;// 

    @Length(max = 100, message = "英文名称长度不能超过100")
    private String nameEn;// 

    private Integer busType;//
    @Length(max = 1024, message = "表说明长度不能超过1024")

    private String description;//

    @Length(max = 128, message = "服务地址长度不能超过128")
    private String serviceUrl;

    private Integer serviceType;

    @Length(max = 1024, message = "参数列表长度不能超过1024")
    private String reqParam;

    private Integer displayType;

    private Integer status;// 

    private Long appId;// 

    private Long ownerId;// 

    private Long createTime;//

    private Long createUser;//

    private Long updateTime;//

    private Long updateUser;//

    private Integer deleted;// 

}