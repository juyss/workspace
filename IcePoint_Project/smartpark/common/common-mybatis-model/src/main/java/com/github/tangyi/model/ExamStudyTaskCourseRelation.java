package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * exam_study_task_course_relation 学习任务课程关系表
 *
 * @author xh
 * @since 2020/10/25
 */
@Table(name = "exam_study_task_course_relation")
public class ExamStudyTaskCourseRelation extends BaseEntity<ExamStudyTaskCourseRelation> {

    private static final long serialVersionUID = 1L;

	/**
	 * task_id 任务ID
	 */
	@Column(name = "task_id") 
	protected Long taskId;

	/**
	 * course_id 课程表id
	 */
	@Column(name = "course_id") 
	protected Long courseId;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

    @Override
    public String toString() {
        return "exam_study_task_course_relation{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", courseId=" + courseId +
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
