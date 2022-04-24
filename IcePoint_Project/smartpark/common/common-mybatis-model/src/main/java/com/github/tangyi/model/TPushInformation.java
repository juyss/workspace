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
 * t_push_information 信息推送列表
 *
 * @author jy
 * @since 2020/11/03
 */
@Table(name = "t_push_information")
public class TPushInformation implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@Column(name = "id")
	protected Long id;

	/**
	 * title 信息推送标题
	 */
	@Column(name = "title") 
	protected String title;

	/**
	 * plate_id 板块id
	 */
	@Column(name = "plate_id") 
	protected Long plateId;

	/**
	 * plate_name 模块名称
	 */
	@Column(name = "plate_name") 
	protected String plateName;

	/**
	 * push_channel_id 推送渠道id: 以逗号形式隔开，如1,2,3
	 */
	@Column(name = "push_channel_id") 
	protected String pushChannelId;

	/**
	 * push_channel_name 推送渠道名称:如门户网站、电子大屏
	 */
	@Column(name = "push_channel_name") 
	protected String pushChannelName;

	/**
	 * is_push 是否自动发布：0否，1是
	 */
	@Column(name = "is_push") 
	protected Integer isPush;

	/**
	 * regular_time 定时发布时间
	 */
	@Column(name = "regular_time") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date regularTime;

	/**
	 * push_time 发布时间
	 */
	@Column(name = "push_time") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date pushTime;

	/**
	 * status 审核状态：0未审核 1审核通过,未发布 2审核失败 3已发布
	 */
	@Column(name = "status") 
	protected Integer status;

	/**
	 * push_content 推送内容
	 */
	@Column(name = "push_content") 
	protected String pushContent;

	/**
	 * policy_push_department 政策发布部门
	 */
	@Column(name = "policy_push_department") 
	protected String policyPushDepartment;

	/**
	 * policy_number 政策文号
	 */
	@Column(name = "policy_number") 
	protected String policyNumber;

	/**
	 * policy_title 政策名称
	 */
	@Column(name = "policy_title") 
	protected String policyTitle;

	/**
	 * theme_image 主题图片
	 */
	@Column(name = "theme_image") 
	protected String themeImage;

	/**
	 * create_data 创建时间
	 */
	@Column(name = "create_data") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date createData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPlateId() {
		return plateId;
	}

	public void setPlateId(Long plateId) {
		this.plateId = plateId;
	}

	public String getPlateName() {
		return plateName;
	}

	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}

	public String getPushChannelId() {
		return pushChannelId;
	}

	public void setPushChannelId(String pushChannelId) {
		this.pushChannelId = pushChannelId;
	}

	public String getPushChannelName() {
		return pushChannelName;
	}

	public void setPushChannelName(String pushChannelName) {
		this.pushChannelName = pushChannelName;
	}

	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	public Date getRegularTime() {
		return regularTime;
	}

	public void setRegularTime(Date regularTime) {
		this.regularTime = regularTime;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPushContent() {
		return pushContent;
	}

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}

	public String getPolicyPushDepartment() {
		return policyPushDepartment;
	}

	public void setPolicyPushDepartment(String policyPushDepartment) {
		this.policyPushDepartment = policyPushDepartment;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyTitle() {
		return policyTitle;
	}

	public void setPolicyTitle(String policyTitle) {
		this.policyTitle = policyTitle;
	}

	public String getThemeImage() {
		return themeImage;
	}

	public void setThemeImage(String themeImage) {
		this.themeImage = themeImage;
	}

	public Date getCreateData() {
		return createData;
	}

	public void setCreateData(Date createData) {
		this.createData = createData;
	}

    @Override
    public String toString() {
        return "t_push_information{" +
                "id=" + id +
                ", title=" + title +
                ", plateId=" + plateId +
                ", plateName=" + plateName +
                ", pushChannelId=" + pushChannelId +
                ", pushChannelName=" + pushChannelName +
                ", isPush=" + isPush +
                ", regularTime=" + regularTime +
                ", pushTime=" + pushTime +
                ", status=" + status +
                ", pushContent=" + pushContent +
                ", policyPushDepartment=" + policyPushDepartment +
                ", policyNumber=" + policyNumber +
                ", policyTitle=" + policyTitle +
                ", themeImage=" + themeImage +
                ", createData=" + createData +
                '}';
    }

}
