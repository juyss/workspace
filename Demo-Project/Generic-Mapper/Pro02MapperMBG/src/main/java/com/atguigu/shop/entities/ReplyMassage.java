package com.atguigu.shop.entities;

import java.util.Date;
import javax.persistence.*;

@Table(name = "msg_message_reply")
public class ReplyMassage {
    /**
     * 主键，留言回复ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 留言ID
     */
    @Column(name = "message_id")
    private Long messageId;

    /**
     * 回复类型，1:留言的回复、0:回复的回复
     */
    @Column(name = "reply_type")
    private Integer replyType;

    /**
     * 回复时间
     */
    @Column(name = "reply_time")
    private Date replyTime;

    /**
     * 回复图片
     */
    @Column(name = "reply_pic")
    private String replyPic;

    /**
     * 回复附件
     */
    @Column(name = "reply_attachment")
    private String replyAttachment;

    /**
     * 回复人id
     */
    @Column(name = "reply_user_id")
    private Long replyUserId;

    /**
     * 回复内容
     */
    @Column(name = "reply_content")
    private String replyContent;

    /**
     * 获取主键，留言回复ID
     *
     * @return id - 主键，留言回复ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键，留言回复ID
     *
     * @param id 主键，留言回复ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取留言ID
     *
     * @return message_id - 留言ID
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * 设置留言ID
     *
     * @param messageId 留言ID
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取回复类型，1:留言的回复、0:回复的回复
     *
     * @return reply_type - 回复类型，1:留言的回复、0:回复的回复
     */
    public Integer getReplyType() {
        return replyType;
    }

    /**
     * 设置回复类型，1:留言的回复、0:回复的回复
     *
     * @param replyType 回复类型，1:留言的回复、0:回复的回复
     */
    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    /**
     * 获取回复时间
     *
     * @return reply_time - 回复时间
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * 设置回复时间
     *
     * @param replyTime 回复时间
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * 获取回复图片
     *
     * @return reply_pic - 回复图片
     */
    public String getReplyPic() {
        return replyPic;
    }

    /**
     * 设置回复图片
     *
     * @param replyPic 回复图片
     */
    public void setReplyPic(String replyPic) {
        this.replyPic = replyPic;
    }

    /**
     * 获取回复附件
     *
     * @return reply_attachment - 回复附件
     */
    public String getReplyAttachment() {
        return replyAttachment;
    }

    /**
     * 设置回复附件
     *
     * @param replyAttachment 回复附件
     */
    public void setReplyAttachment(String replyAttachment) {
        this.replyAttachment = replyAttachment;
    }

    /**
     * 获取回复人id
     *
     * @return reply_user_id - 回复人id
     */
    public Long getReplyUserId() {
        return replyUserId;
    }

    /**
     * 设置回复人id
     *
     * @param replyUserId 回复人id
     */
    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    /**
     * 获取回复内容
     *
     * @return reply_content - 回复内容
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 设置回复内容
     *
     * @param replyContent 回复内容
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}