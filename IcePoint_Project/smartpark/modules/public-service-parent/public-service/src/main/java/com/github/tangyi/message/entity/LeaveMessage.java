package com.github.tangyi.message.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: LeaveMessage
 * @Desc: 留言表实体类
 * @package com.github.tangyi.message.entity
 * @project park
 * @date 2021/3/27 10:36
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "msg_interaction_message")
public class LeaveMessage {
    /**
     * 主键，留言ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("留言主键ID")
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
    private String postalCode;

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
     * 审核状态，1:通过、0:不通过
     */
    @Column(name = "check_status")
    private Integer checkStatus;

    /**
     * 受理状态，1:已受理、0:未受理
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
     * 此字段在数据库中不存在，作为回复信息的封装字段
     */
    @Transient
    private ReplyMessage replyMessage;
}
