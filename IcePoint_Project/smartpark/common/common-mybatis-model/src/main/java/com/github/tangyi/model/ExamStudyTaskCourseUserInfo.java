package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * exam_study_task_course_user_info 用户任务课程学习情况
 *
 * @author xh
 * @since 2020/10/25
 */
@Table(name = "exam_study_task_course_user_info")
public class ExamStudyTaskCourseUserInfo extends BaseEntity<ExamStudyTaskCourseUserInfo> {

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
	 * exam_course_id 课程ID
	 */
	@Column(name = "exam_course_id") 
	protected Long examCourseId;

	/**
	 * study_time 学习时长（秒）
	 */
	@Column(name = "study_time") 
	protected Integer studyTime;

	/**
	 * is_finish 0:未完成，1：已完成
	 */
	@Column(name = "is_finish") 
	protected Integer isFinish;

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

	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}

	public Integer getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(Integer studyTime) {
		this.studyTime = studyTime;
	}

	public Integer getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

    @Override
    public String toString() {
        return "exam_study_task_course_user_info{" +
                "id=" + id +
                ", userId=" + userId +
                ", taskId=" + taskId +
                ", examCourseId=" + examCourseId +
                ", studyTime=" + studyTime +
                ", isFinish=" + isFinish +
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
