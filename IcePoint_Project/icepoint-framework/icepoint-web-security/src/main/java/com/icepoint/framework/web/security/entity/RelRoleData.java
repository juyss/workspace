package com.icepoint.framework.web.security.entity;

import com.icepoint.framework.data.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//角色数据关系表
//定义了角色有那些数据的权限
@Table(name = "pmi_rel_role_data")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class RelRoleData extends BaseEntity<Long> {
	/**
	 * 角色
	 */
    @Column(name = "`role_code`")
    private String roleCode;
    
	/**
	 * 数据
	 * 使用数据资产定义作为数据权限基本单位
	 */
    @Column(name = "`asset_def_Id`")
    private Long assetDefId;
    
    //看是否要加个类型 1-读 2-写
}
