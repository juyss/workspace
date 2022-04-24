package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * exam_study_task 学习任务表
 *
 * @author xh
 * @since 2020/12/12
 */
@Table(name = "exam_study_task")
public class ExamStudyTask extends BaseEntity<ExamStudyTask> {

    private static final long serialVersionUID = 1L;

	/**
	 * task_name 任务名称
	 */
	@Column(name = "task_name") 
	protected String taskName;

	/**
	 * task_description 任务描述
	 */
	@Column(name = "task_description") 
	protected String taskDescription;

	/**
	 * status 状态0草稿1发布
	 */
	@Column(name = "status") 
	protected Integer status;

	/**
	 * audit_user_name 审核人
	 */
	@Column(name = "audit_user_name") 
	protected String auditUserName;

	/**
	 * audit_user_id 审核人id
	 */
	@Column(name = "audit_user_id") 
	protected Long auditUserId;

	/**
	 * course_num 课程数
	 */
	@Column(name = "course_num") 
	protected Integer courseNum;

	/**
	 * user_num 学习人数
	 */
	@Column(name = "user_num") 
	protected Integer userNum;

	/**
	 * is_public 是否公开 0 私有 1 公开
	 */
	@Column(name = "is_public") 
	protected Integer isPublic;

	/**
	 * total_study_time 总学习时长
	 */
	@Column(name = "total_study_time") 
	protected Integer totalStudyTime;

	/**
	 * major 专业分类
	 */
	@Column(name = "major") 
	protected String major;

	/**
	 * pic 封面图片
	 */
	@Column(name = "pic") 
	protected String pic;

	/**
	 * status_a 状态a 0 无效 1有效
	 */
	@Column(name = "status_a") 
	protected Integer statusA;

	/**
	 * status_a_user_id 状态a变更者
	 */
	@Column(name = "status_a_user_id") 
	protected Long statusAUserId;

	/**
	 * status_a_user_name 状态a变更者姓名
	 */
	@Column(name = "status_a_user_name") 
	protected String statusAUserName;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public Long getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getTotalStudyTime() {
		return totalStudyTime;
	}

	public void setTotalStudyTime(Integer totalStudyTime) {
		this.totalStudyTime = totalStudyTime;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getStatusA() {
		return statusA;
	}

	public void setStatusA(Integer statusA) {
		this.statusA = statusA;
	}

	public Long getStatusAUserId() {
		return statusAUserId;
	}

	public void setStatusAUserId(Long statusAUserId) {
		this.statusAUserId = statusAUserId;
	}

	public String getStatusAUserName() {
		return statusAUserName;
	}

	public void setStatusAUserName(String statusAUserName) {
		this.statusAUserName = statusAUserName;
	}

    @Override
    public String toString() {
        return "exam_study_task{" +
                "id=" + id +
                ", taskName=" + taskName +
                ", taskDescription=" + taskDescription +
                ", creator=" + creator +
                ", createDate=" + createDate +
                ", modifier=" + modifier +
                ", modifyDate=" + modifyDate +
                ", delFlag=" + delFlag +
                ", applicationCode=" + applicationCode +
                ", tenantCode=" + tenantCode +
                ", status=" + status +
                ", auditUserName=" + auditUserName +
                ", auditUserId=" + auditUserId +
                ", courseNum=" + courseNum +
                ", userNum=" + userNum +
                ", isPublic=" + isPublic +
                ", totalStudyTime=" + totalStudyTime +
                ", major=" + major +
                ", pic=" + pic +
                ", statusA=" + statusA +
                ", statusAUserId=" + statusAUserId +
                ", statusAUserName=" + statusAUserName +
                '}';
    }

}
