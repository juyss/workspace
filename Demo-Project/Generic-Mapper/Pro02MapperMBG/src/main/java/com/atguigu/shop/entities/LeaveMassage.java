package com.atguigu.shop.entities;

import java.util.Date;
import javax.persistence.*;

@Table(name = "msg_interaction_message")
public class LeaveMassage {
    /**
     * 主键，留言ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 留言者网名
     */
    @Column(name = "internet_name")
    private String internetName;

    /**
     * 邮箱
     */
    @Column(name = "email_address")
    private String emailAddress;

    /**
     * 邮政编码
     */
    @Column(name = "postal_code")
    private Integer postalCode;

    /**
     * 居住地址
     */
    @Column(name = "residential_address")
    private String residentialAddress;

    /**
     * 留言类型，1:咨询、2:建议、3:投诉、4:分享
     */
    @Column(name = "message_type")
    private Integer messageType;

    /**
     * 留言主题
     */
    @Column(name = "message_theme")
    private String messageTheme;

    /**
     * 留言图片
     */
    @Column(name = "message_pic")
    private String messagePic;

    /**
     * 留言附件
     */
    @Column(name = "message_attachment")
    private String messageAttachment;

    /**
     * 手机号
     */
    @Column(name = "phone_num")
    private String phoneNum;

    /**
     * 留言创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 处理人ID
     */
    @Column(name = "modifier_id")
    private Long modifierId;

    /**
     * 最后处理时间
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 审核状态，1:通过、2:不通过
     */
    @Column(name = "check_status")
    private Integer checkStatus;

    /**
     * 受理状态，1:已受理、2:未受理
     */
    @Column(name = "reply_status")
    private Integer replyStatus;

    /**
     * 删除标记 0:正常;1:删除
     */
    @Column(name = "del_flag")
    private Integer delFlag;

    /**
     * 留言内容
     */
    @Column(name = "message_content")
    private String messageContent;

    /**
     * 获取主键，留言ID
     *
     * @return id - 主键，留言ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键，留言ID
     *
     * @param id 主键，留言ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取留言者网名
     *
     * @return internet_name - 留言者网名
     */
    public String getInternetName() {
        return internetName;
    }

    /**
     * 设置留言者网名
     *
     * @param internetName 留言者网名
     */
    public void setInternetName(String internetName) {
        this.internetName = internetName;
    }

    /**
     * 获取邮箱
     *
     * @return email_address - 邮箱
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * 设置邮箱
     *
     * @param emailAddress 邮箱
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * 获取邮政编码
     *
     * @return postal_code - 邮政编码
     */
    public Integer getPostalCode() {
        return postalCode;
    }

    /**
     * 设置邮政编码
     *
     * @param postalCode 邮政编码
     */
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 获取居住地址
     *
     * @return residential_address - 居住地址
     */
    public String getResidentialAddress() {
        return residentialAddress;
    }

    /**
     * 设置居住地址
     *
     * @param residentialAddress 居住地址
     */
    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    /**
     * 获取留言类型，1:咨询、2:建议、3:投诉、4:分享
     *
     * @return message_type - 留言类型，1:咨询、2:建议、3:投诉、4:分享
     */
    public Integer getMessageType() {
        return messageType;
    }

    /**
     * 设置留言类型，1:咨询、2:建议、3:投诉、4:分享
     *
     * @param messageType 留言类型，1:咨询、2:建议、3:投诉、4:分享
     */
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /**
     * 获取留言主题
     *
     * @return message_theme - 留言主题
     */
    public String getMessageTheme() {
        return messageTheme;
    }

    /**
     * 设置留言主题
     *
     * @param messageTheme 留言主题
     */
    public void setMessageTheme(String messageTheme) {
        this.messageTheme = messageTheme;
    }

    /**
     * 获取留言图片
     *
     * @return message_pic - 留言图片
     */
    public String getMessagePic() {
        return messagePic;
    }

    /**
     * 设置留言图片
     *
     * @param messagePic 留言图片
     */
    public void setMessagePic(String messagePic) {
        this.messagePic = messagePic;
    }

    /**
     * 获取留言附件
     *
     * @return message_attachment - 留言附件
     */
    public String getMessageAttachment() {
        return messageAttachment;
    }

    /**
     * 设置留言附件
     *
     * @param messageAttachment 留言附件
     */
    public void setMessageAttachment(String messageAttachment) {
        this.messageAttachment = messageAttachment;
    }

    /**
     * 获取手机号
     *
     * @return phone_num - 手机号
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置手机号
     *
     * @param phoneNum 手机号
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * 获取留言创建时间
     *
     * @return create_date - 留言创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置留言创建时间
     *
     * @param createDate 留言创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取处理人ID
     *
     * @return modifier_id - 处理人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 设置处理人ID
     *
     * @param modifierId 处理人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 获取最后处理时间
     *
     * @return modify_date - 最后处理时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置最后处理时间
     *
     * @param modifyDate 最后处理时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取审核状态，1:通过、2:不通过
     *
     * @return check_status - 审核状态，1:通过、2:不通过
     */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置审核状态，1:通过、2:不通过
     *
     * @param checkStatus 审核状态，1:通过、2:不通过
     */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * 获取受理状态，1:已受理、2:未受理
     *
     * @return reply_status - 受理状态，1:已受理、2:未受理
     */
    public Integer getReplyStatus() {
        return replyStatus;
    }

    /**
     * 设置受理状态，1:已受理、2:未受理
     *
     * @param replyStatus 受理状态，1:已受理、2:未受理
     */
    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    /**
     * 获取删除标记 0:正常;1:删除
     *
     * @return del_flag - 删除标记 0:正常;1:删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标记 0:正常;1:删除
     *
     * @param delFlag 删除标记 0:正常;1:删除
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取留言内容
     *
     * @return message_content - 留言内容
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * 设置留言内容
     *
     * @param messageContent 留言内容
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}