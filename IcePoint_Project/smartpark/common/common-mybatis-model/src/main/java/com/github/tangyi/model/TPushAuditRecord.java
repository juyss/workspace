package com.github.tangyi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_push_audit_record 信息推送审核记录表
 *
 * @author jy
 * @since 2020/11/03
 */
@Table(name = "t_push_audit_record")
public class TPushAuditRecord implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@Column(name = "id") 
	protected Long id;

	/**
	 * information_push_id 信息推送表id
	 */
	@Column(name = "information_push_id") 
	protected Long informationPushId;

	/**
	 * content 审核意见
	 */
	@Column(name = "content") 
	protected String content;

	/**
	 * operation_time 操作时间
	 */
	@Column(name = "operation_time") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date operationTime;

	/**
	 * status 审核状态： 1审核通过 2审核失败
	 */
	@Column(name = "status") 
	protected Integer status;

	/**
	 * operation_user_id 操作用户id
	 */
	@Column(name = "operation_user_id") 
	protected Long operationUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInformationPushId() {
		return informationPushId;
	}

	public void setInformationPushId(Long informationPushId) {
		this.informationPushId = informationPushId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOperationUserId() {
		return operationUserId;
	}

	public void setOperationUserId(Long operationUserId) {
		this.operationUserId = operationUserId;
	}

    @Override
    public String toString() {
        return "t_push_audit_record{" +
                "id=" + id +
                ", informationPushId=" + informationPushId +
                ", content=" + content +
                ", operationTime=" + operationTime +
                ", status=" + status +
                ", operationUserId=" + operationUserId +
                '}';
    }

}
