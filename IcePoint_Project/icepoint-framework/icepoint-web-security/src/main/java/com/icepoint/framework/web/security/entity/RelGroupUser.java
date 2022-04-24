package com.icepoint.framework.web.security.entity;

import com.icepoint.framework.data.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//用户组用户关系表
@Table(name = "pmi_rel_group_user")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class RelGroupUser extends BaseEntity<Long> {
	/**
	 * 用户组
	 */
    @Column(name = "`group_code`")
    private String groupCode;
    
	/**
	 * 用户ID
	 */
    @Column(name = "`cust_id`")
    private Long custId;
}
