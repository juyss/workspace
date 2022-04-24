package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * train_user_relation 培训报名用户表
 *
 * @author xh
 * @since 2020/10/20
 */
@Table(name = "train_user_relation")
public class TrainUserRelation extends BaseEntity<TrainUserRelation> {

    private static final long serialVersionUID = 1L;

	/**
	 * train_course_id 培训课程id
	 */
	@Column(name = "train_course_id")
    
	protected Long trainCourseId;

	/**
	 * user_id 用户ID
	 */
	@Column(name = "user_id")
    
	protected Long userId;

	/**
	 * is_sign_in 是否签到
	 */
	@Column(name = "is_sign_in")
    
	protected Integer isSignIn;

	/**
	 * sign_in_time 签到时间
	 */
	@Column(name = "sign_in_time")
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date signInTime;

	public Long getTrainCourseId() {
		return trainCourseId;
	}

	public void setTrainCourseId(Long trainCourseId) {
		this.trainCourseId = trainCourseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsSignIn() {
		return isSignIn;
	}

	public void setIsSignIn(Integer isSignIn) {
		this.isSignIn = isSignIn;
	}

	public Date getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(Date signInTime) {
		this.signInTime = signInTime;
	}

    @Override
    public String toString() {
        return "train_user_relation{" +
                "id=" + id +
                ", trainCourseId=" + trainCourseId +
                ", userId=" + userId +
                ", isSignIn=" + isSignIn +
                ", signInTime=" + signInTime +
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
