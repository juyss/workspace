package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * train_course 培训表
 *
 * @author xh
 * @since 2020/10/20
 */
@Table(name = "train_course")
public class TrainCourse extends BaseEntity<TrainCourse> {

    private static final long serialVersionUID = 1L;

	/**
	 * course_name 培训课程名称
	 */
	@Column(name = "course_name")
    
	protected String courseName;

	/**
	 * teacher 老师
	 */
	@Column(name = "teacher")
    
	protected String teacher;

	/**
	 * start_time 开始时间
	 */
	@Column(name = "start_time")
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date startTime;

	/**
	 * end_time 截止时间
	 */
	@Column(name = "end_time")
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date endTime;

	/**
	 * sign_up_start_time 报名开始时间
	 */
	@Column(name = "sign_up_start_time")
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date signUpStartTime;

	/**
	 * place 培训地点
	 */
	@Column(name = "place")
    
	protected String place;

	/**
	 * sign_up_end_time 报名截止时间
	 */
	@Column(name = "sign_up_end_time")
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date signUpEndTime;

	/**
	 * audit_user_id 审核人
	 */
	@Column(name = "audit_user_id")
    
	protected Long auditUserId;

	/**
	 * audit_user_name
	 */
	@Column(name = "audit_user_name")
    
	protected String auditUserName;

	/**
	 * course_introduce 培训课程介绍
	 */
	@Column(name = "course_introduce")
    
	protected String courseIntroduce;

	/**
	 * status 审核状态： 0,未审核，1已审核
	 */
	@Column(name = "status")
    
	protected Integer status;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getSignUpStartTime() {
		return signUpStartTime;
	}

	public void setSignUpStartTime(Date signUpStartTime) {
		this.signUpStartTime = signUpStartTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getSignUpEndTime() {
		return signUpEndTime;
	}

	public void setSignUpEndTime(Date signUpEndTime) {
		this.signUpEndTime = signUpEndTime;
	}

	public Long getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getCourseIntroduce() {
		return courseIntroduce;
	}

	public void setCourseIntroduce(String courseIntroduce) {
		this.courseIntroduce = courseIntroduce;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    @Override
    public String toString() {
        return "train_course{" +
                "id=" + id +
                ", courseName=" + courseName +
                ", teacher=" + teacher +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", signUpStartTime=" + signUpStartTime +
                ", place=" + place +
                ", signUpEndTime=" + signUpEndTime +
                ", auditUserId=" + auditUserId +
                ", auditUserName=" + auditUserName +
                ", creator=" + creator +
                ", createDate=" + createDate +
                ", modifier=" + modifier +
                ", modifyDate=" + modifyDate +
                ", delFlag=" + delFlag +
                ", applicationCode=" + applicationCode +
                ", courseIntroduce=" + courseIntroduce +
                ", tenantCode=" + tenantCode +
                ", status=" + status +
                '}';
    }

}
