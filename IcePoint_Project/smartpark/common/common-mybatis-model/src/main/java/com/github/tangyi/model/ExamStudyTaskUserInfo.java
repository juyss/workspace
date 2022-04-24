package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * exam_study_task_user_info 学习任务用户关联表
 *
 * @author xh
 * @since 2020/10/25
 */
@Table(name = "exam_study_task_user_info")
public class ExamStudyTaskUserInfo extends BaseEntity<ExamStudyTaskUserInfo> {

    private static final long serialVersionUID = 1L;

	/**
	 * user_id 用户ID
	 */
	@Column(name = "user_id") 
	protected Long userId;

	/**
	 * task_id 任务ID
	 */
	@Column(name = "task_id") 
	protected Long taskId;

	/**
	 * study_time 学习时长（秒）
	 */
	@Column(name = "study_time") 
	protected Integer studyTime;

	/**
	 * course_num 课程数
	 */
	@Column(name = "course_num") 
	protected Integer courseNum;

	/**
	 * study_status wait 未开始，doing进行中，finish 完成
	 */
	@Column(name = "study_status") 
	protected String studyStatus;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Integer getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(Integer studyTime) {
		this.studyTime = studyTime;
	}

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}

	public String getStudyStatus() {
		return studyStatus;
	}

	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}

    @Override
    public String toString() {
        return "exam_study_task_user_info{" +
                "id=" + id +
                ", userId=" + userId +
                ", taskId=" + taskId +
                ", studyTime=" + studyTime +
                ", creator=" + creator +
                ", createDate=" + createDate +
                ", modifier=" + modifier +
                ", modifyDate=" + modifyDate +
                ", delFlag=" + delFlag +
                ", applicationCode=" + applicationCode +
                ", tenantCode=" + tenantCode +
                ", courseNum=" + courseNum +
                ", studyStatus=" + studyStatus +
                '}';
    }

}
