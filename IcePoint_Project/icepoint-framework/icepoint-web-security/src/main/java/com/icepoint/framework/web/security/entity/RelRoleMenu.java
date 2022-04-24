package com.icepoint.framework.web.security.entity;

import com.icepoint.framework.data.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色菜单关系表
 * 定义了角色具备那些页面那些按钮的操作权限
 */
@Table(name = "pmi_rel_role_menu")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class RelRoleMenu extends BaseEntity<Long> {
	/**
	 * 角色
	 */
    @Column(name = "`role_code`")
    private String roleCode;
    
	/**
	 * 菜单
	 */
    @Column(name = "`menu_code`")
    private String menuCode;
    
}
