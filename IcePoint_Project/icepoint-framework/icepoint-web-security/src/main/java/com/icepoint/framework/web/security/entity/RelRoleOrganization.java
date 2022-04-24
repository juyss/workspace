package com.icepoint.framework.web.security.entity;

import com.icepoint.framework.data.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//角色部门关系表
//在数据权限基础上定义了角色有那些部门数据的权限
@Table(name = "pmi_rel_role_org")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class RelRoleOrganization extends BaseEntity<Long> {
	/**
	 * 角色
	 */
    @Column(name = "`role_code`")
    private String roleCode;
    
	/**
	 * 部门
	 */
    @Column(name = "`org_Id`")
    private Long orgId;
}
