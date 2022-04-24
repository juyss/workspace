package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * main_role 主系统角色
 *
 * @author xh
 * @since 2020/11/05
 */
@Table(name = "main_role")
public class MainRole extends BaseEntity<MainRole> {

    private static final long serialVersionUID = 1L;

	/**
	 * role_id 主系统角色id
	 */
	@Column(name = "role_id") 
	protected Integer roleId;

	/**
	 * role_name 主系统角色名
	 */
	@Column(name = "role_name") 
	protected String roleName;

	/**
	 * role_code 主系统角色码
	 */
	@Column(name = "role_code") 
	protected String roleCode;

	/**
	 * note 主系统备注
	 */
	@Column(name = "note") 
	protected String note;

	/**
	 * order_no 主系统序号
	 */
	@Column(name = "order_no") 
	protected Integer orderNo;

	/**
	 * sub_role_id 当前系统角色id
	 */
	@Column(name = "sub_role_id") 
	protected Long subRoleId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Long getSubRoleId() {
		return subRoleId;
	}

	public void setSubRoleId(Long subRoleId) {
		this.subRoleId = subRoleId;
	}

    @Override
    public String toString() {
        return "main_role{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", roleName=" + roleName +
                ", roleCode=" + roleCode +
                ", note=" + note +
                ", orderNo=" + orderNo +
                ", subRoleId=" + subRoleId +
                ", creator=" + creator +
                ", createDate=" + createDate +
                ", modifier=" + modifier +
                ", modifyDate=" + modifyDate +
                ", delFlag=" + delFlag +
                ", applicationCode=" + applicationCode +
                ", tenantCode=" + tenantCode +
                '}';
    }

}
