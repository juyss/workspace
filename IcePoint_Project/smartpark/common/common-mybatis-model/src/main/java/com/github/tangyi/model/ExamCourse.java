package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * exam_course 课程表
 *
 * @author xh
 * @since 2020/12/12
 */
@Table(name = "exam_course")
public class ExamCourse extends BaseEntity<ExamCourse> {

    private static final long serialVersionUID = 1L;

	/**
	 * course_name 课程名称
	 */
	@Column(name = "course_name") 
	protected String courseName;

	/**
	 * college 学院
	 */
	@Column(name = "college") 
	protected String college;

	/**
	 * major 专业
	 */
	@Column(name = "major") 
	protected String major;

	/**
	 * teacher 老师
	 */
	@Column(name = "teacher") 
	protected String teacher;

	/**
	 * course_introduce 课程简介
	 */
	@Column(name = "course_introduce") 
	protected String courseIntroduce;

	/**
	 * course_description 课程描述
	 */
	@Column(name = "course_description") 
	protected String courseDescription;

	/**
	 * course_type 课程类型(0:图文课程,1:视频课程)
	 */
	@Column(name = "course_type") 
	protected String courseType;

	/**
	 * attachment_id 视频课程 附件id
	 */
	@Column(name = "attachment_id") 
	protected Long attachmentId;

	/**
	 * course_time 课程时长
	 */
	@Column(name = "course_time") 
	protected Integer courseTime;

	/**
	 * study_time 最少学习时长(秒)
	 */
	@Column(name = "study_time") 
	protected Integer studyTime;

	/**
	 * audit_user_id 审核人id
	 */
	@Column(name = "audit_user_id") 
	protected Long auditUserId;

	/**
	 * audit_user_name 审核人
	 */
	@Column(name = "audit_user_name") 
	protected String auditUserName;

	/**
	 * status 状态0停用1启用
	 */
	@Column(name = "status") 
	protected Integer status;

	/**
	 * pic 封面图片
	 */
	@Column(name = "pic") 
	protected String pic;

	/**
	 * video_pic 视频缩略图片
	 */
	@Column(name = "video_pic") 
	protected String videoPic;

	/**
	 * video 视频url
	 */
	@Column(name = "video") 
	protected String video;

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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getCourseIntroduce() {
		return courseIntroduce;
	}

	public void setCourseIntroduce(String courseIntroduce) {
		this.courseIntroduce = courseIntroduce;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Integer getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(Integer courseTime) {
		this.courseTime = courseTime;
	}

	public Integer getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(Integer studyTime) {
		this.studyTime = studyTime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getVideoPic() {
		return videoPic;
	}

	public void setVideoPic(String videoPic) {
		this.videoPic = videoPic;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
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
        return "exam_course{" +
                "id=" + id +
                ", courseName=" + courseName +
                ", college=" + college +
                ", major=" + major +
                ", teacher=" + teacher +
                ", courseIntroduce=" + courseIntroduce +
                ", courseDescription=" + courseDescription +
                ", courseType=" + courseType +
                ", attachmentId=" + attachmentId +
                ", courseTime=" + courseTime +
                ", studyTime=" + studyTime +
                ", auditUserId=" + auditUserId +
                ", auditUserName=" + auditUserName +
                ", status=" + status +
                ", pic=" + pic +
                ", creator=" + creator +
                ", createDate=" + createDate +
                ", modifier=" + modifier +
                ", modifyDate=" + modifyDate +
                ", delFlag=" + delFlag +
                ", applicationCode=" + applicationCode +
                ", tenantCode=" + tenantCode +
                ", videoPic=" + videoPic +
                ", video=" + video +
                ", statusA=" + statusA +
                ", statusAUserId=" + statusAUserId +
                ", statusAUserName=" + statusAUserName +
                '}';
    }

}
