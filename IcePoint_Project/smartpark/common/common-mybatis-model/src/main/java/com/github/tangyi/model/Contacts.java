package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * contacts 通讯录
 *
 * @author xh
 * @since 2020/11/13
 */
@Table(name = "contacts")
public class Contacts extends BaseEntity<Contacts> {

    private static final long serialVersionUID = 1L;

	/**
	 * dept_id 部门id
	 */
	@Column(name = "dept_id") 
	protected Long deptId;

	/**
	 * type 通讯录类型： 园区yuanqu，企业qiye，社区shequ
	 */
	@Column(name = "type") 
	protected String type;

	/**
	 * name 姓名
	 */
	@Column(name = "name") 
	protected String name;

	/**
	 * phone 手机号
	 */
	@Column(name = "phone") 
	protected String phone;

	/**
	 * tel 电话
	 */
	@Column(name = "tel") 
	protected String tel;

	/**
	 * position 职务
	 */
	@Column(name = "position") 
	protected String position;

	/**
	 * email email
	 */
	@Column(name = "email") 
	protected String email;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Override
    public String toString() {
        return "contacts{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", type=" + type +
                ", name=" + name +
                ", phone=" + phone +
                ", tel=" + tel +
                ", position=" + position +
                ", email=" + email +
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
