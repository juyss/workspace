package com.github.tangyi.message.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: ReplyMessage
 * @Desc: 留言回复表实体类
 * @package com.github.tangyi.message.entity
 * @project park
 * @date 2021/3/27 10:48
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "msg_message_reply")
public class ReplyMessage {
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
     * 回复人名字
     */
    @Column(name = "reply_user_name")
    private String replyUserName;

}
