package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * main_user 
 *
 * @author xh
 * @since 2020/11/05
 */
@Table(name = "main_user")
public class MainUser extends BaseEntity<MainUser> {

    private static final long serialVersionUID = 1L;

	/**
	 * user_id 金证主系统的用户id
	 */
	@Column(name = "user_id") 
	protected Integer userId;

	/**
	 * gender 金证主系统的用户性别
	 */
	@Column(name = "gender") 
	protected Integer gender;

	/**
	 * login_name 金证主系统的用户登陆用户名
	 */
	@Column(name = "login_name") 
	protected String loginName;

	/**
	 * user_name 金证主系统的用户用户姓名
	 */
	@Column(name = "user_name") 
	protected String userName;

	/**
	 * user_mobile 金证主系统的用户手机号
	 */
	@Column(name = "user_mobile") 
	protected String userMobile;

	/**
	 * user_mail 金证主系统的用户email
	 */
	@Column(name = "user_mail") 
	protected String userMail;

	/**
	 * user_oph 金证主系统的用户电话
	 */
	@Column(name = "user_oph") 
	protected String userOph;

	/**
	 * note 金证主系统的用户备注
	 */
	@Column(name = "note") 
	protected String note;

	/**
	 * sub_user_id 本系统的用户id
	 */
	@Column(name = "sub_user_id") 
	protected Long subUserId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserOph() {
		return userOph;
	}

	public void setUserOph(String userOph) {
		this.userOph = userOph;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getSubUserId() {
		return subUserId;
	}

	public void setSubUserId(Long subUserId) {
		this.subUserId = subUserId;
	}

    @Override
    public String toString() {
        return "main_user{" +
                "id=" + id +
                ", userId=" + userId +
                ", gender=" + gender +
                ", loginName=" + loginName +
                ", userName=" + userName +
                ", userMobile=" + userMobile +
                ", userMail=" + userMail +
                ", userOph=" + userOph +
                ", note=" + note +
                ", subUserId=" + subUserId +
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
