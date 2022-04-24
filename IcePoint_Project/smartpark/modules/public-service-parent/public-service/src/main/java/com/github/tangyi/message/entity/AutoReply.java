package com.github.tangyi.message.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 自动回复实体类
 * @author Juyss
 * @version 1.0
 * @ClassName AutoReply
 * @description 自动回复实体类
 * @date 2021-05-19 9:52
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="generate.MsgAutoReply自动回复信息表")
@Table(name = "msg_auto_reply")
@Data
public class AutoReply implements Serializable {
    /**
     * 主键，自动回复ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value="主键，自动回复ID")
    private Long id;

    /**
     * 自动回复主题
     */
    @Column(name = "auto_reply_theme")
    @ApiModelProperty(value="自动回复主题")
    private String autoReplyTheme;

    /**
     * 自动回复内容
     */
    @Column(name = "auto_reply_content")
    @ApiModelProperty(value="自动回复内容")
    private String autoReplyContent;

    /**
     * 自动回复创建时间
     */
    @Column(name = "create_date")
    @ApiModelProperty(value="自动回复创建时间")
    private Date createDate;

    /**
     * 创建者id
     */
    @Column(name = "create_user_id")
    @ApiModelProperty(value="创建者id")
    private Long createUserId;

    /**
     * 创建者用户名
     */
    @Column(name = "create_user_name")
    @ApiModelProperty(value="创建者用户名")
    private String createUserName;

    /**
     * 最后处理时间
     */
    @Column(name = "modify_date")
    @ApiModelProperty(value="最后处理时间")
    private Date modifyDate;

    /**
     * 删除标记 0:正常;1:删除
     */
    @Column(name = "del_flag")
    @ApiModelProperty(value="删除标记 0:正常;1:删除")
    private Integer delFlag;

    @Transient
    private static final long serialVersionUID = 1L;
}
