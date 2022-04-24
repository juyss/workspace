package com.icepoint.framework.web.security.entity;

import com.icepoint.framework.data.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//用户组角色关系表
@Table(name = "pmi_rel_group_role")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class RelGroupRole extends BaseEntity<Long> {
	/**
	 * 用户组
	 */
    @Column(name = "`group_code`")
    private String groupCode;
    
	/**
	 * 角色
	 */
    @Column(name = "`role_code`")
    private String roleCode;
}
