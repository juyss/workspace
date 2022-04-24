package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * page_click_log 页点点击日志表
 *
 * @author xh
 * @since 2020/11/13
 */
@Table(name = "page_click_log")
public class PageClickLog extends BaseEntity<PageClickLog> {

    private static final long serialVersionUID = 1L;

	/**
	 * click_id 点击Id
	 */
	@Column(name = "click_id") 
	protected String clickId;

	/**
	 * click_type 点击类型
	 */
	@Column(name = "click_type") 
	protected String clickType;

	/**
	 * click_ip 点击Ip
	 */
	@Column(name = "click_ip") 
	protected String clickIp;

	/**
	 * click_date 点击日期
	 */
	@Column(name = "click_date") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date clickDate;

	/**
	 * click_count 点击次数
	 */
	@Column(name = "click_count") 
	protected Long clickCount;

	/**
	 * identifier 用户唯一标识
	 */
	@Column(name = "identifier") 
	protected String identifier;

	/**
	 * user_id 用户id
	 */
	@Column(name = "user_id") 
	protected Long userId;

	public String getClickId() {
		return clickId;
	}

	public void setClickId(String clickId) {
		this.clickId = clickId;
	}

	public String getClickType() {
		return clickType;
	}

	public void setClickType(String clickType) {
		this.clickType = clickType;
	}

	public String getClickIp() {
		return clickIp;
	}

	public void setClickIp(String clickIp) {
		this.clickIp = clickIp;
	}

	public Date getClickDate() {
		return clickDate;
	}

	public void setClickDate(Date clickDate) {
		this.clickDate = clickDate;
	}

	public Long getClickCount() {
		return clickCount;
	}

	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    @Override
    public String toString() {
        return "page_click_log{" +
                "id=" + id +
                ", clickId=" + clickId +
                ", clickType=" + clickType +
                ", clickIp=" + clickIp +
                ", clickDate=" + clickDate +
                ", clickCount=" + clickCount +
                ", identifier=" + identifier +
                ", userId=" + userId +
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
