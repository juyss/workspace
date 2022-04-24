package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * sms_record 短信发送记录
 *
 * @author xh
 * @since 2020/12/03
 */
@Table(name = "sms_record")
public class SmsRecord extends BaseEntity<SmsRecord> {

    private static final long serialVersionUID = 1L;

	/**
	 * content 发送内容
	 */
	@Column(name = "content") 
	protected String content;

	/**
	 * mobile 手机号
	 */
	@Column(name = "mobile") 
	protected String mobile;

	/**
	 * send_by 发送平台 aliyu 阿里云 rhyx 融合云信
	 */
	@Column(name = "send_by") 
	protected String sendBy;

	/**
	 * success 是否发送成功 0 否 1 是
	 */
	@Column(name = "success") 
	protected Integer success;

	/**
	 * res_json 平台api返回json
	 */
	@Column(name = "res_json") 
	protected String resJson;

	/**
	 * templetid 模板id
	 */
	@Column(name = "templetid") 
	protected String templetid;

	/**
	 * templet_name 模板名称
	 */
	@Column(name = "templet_name") 
	protected String templetName;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSendBy() {
		return sendBy;
	}

	public void setSendBy(String sendBy) {
		this.sendBy = sendBy;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public String getResJson() {
		return resJson;
	}

	public void setResJson(String resJson) {
		this.resJson = resJson;
	}

	public String getTempletid() {
		return templetid;
	}

	public void setTempletid(String templetid) {
		this.templetid = templetid;
	}

	public String getTempletName() {
		return templetName;
	}

	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}

    @Override
    public String toString() {
        return "sms_record{" +
                "id=" + id +
                ", content=" + content +
                ", mobile=" + mobile +
                ", sendBy=" + sendBy +
                ", success=" + success +
                ", resJson=" + resJson +
                ", templetid=" + templetid +
                ", templetName=" + templetName +
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
