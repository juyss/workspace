package com.icepoint.base.web.info.entity;

import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 资讯
 *
 */
@Table
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Information extends BasicEntity<Long> {
    /**
     * 栏目类型
     */
    private Integer columnType;
    /**
     * 栏目ID
     */
    private Long columnId;
    /**
     * 业务码
     */
    @Length(max = 16, message = "业务码长度不能超过16")
    private String bizCode;
    private Long authorCustId;
    @Length(max = 32, message = "发布者名字长度不能超过32")
    private String authorName;
    /**
     * 区域代码
     */
    @Length(max = 64, message = "区域代码长度不能超过64")
    private String adcode;
    /**
     * 来源
     */
    @Length(max = 32, message = "来源长度不能超过32")
    private String source;
    @Length(max = 128, message = "来源图标长度不能超过128")
    private String sourceIcon;
    /**
     * 标题
     */
    @Length(max = 64, message = "标题长度不能超过64")
    private String title;
    /**
     * 概要
     */
    @Length(max = 1024, message = "概要长度不能超过1024")
    private String summary;
    /**
     * 主题图片路径
     */
    @Length(max = 128, message = "主题图片路径长度不能超过128")
    private String topicPhoto;
    /**
     * 视频音频
     */
    @Length(max = 128, message = "视频音频路径长度不能超过128")
    private String video;
    /**
     * 时长
     */
    private Integer duration;
    /**
     * 内容类型
     * 数据字典:1-文本  2-图文  5-文章/链接  6-音频  7-视频
     */
    private Integer contentType;
    /**
     * 内容路径
     */
    private String content;
    private Long addrId;
    @Length(max = 128, message = "位置名称长度不能超过128")
    private String addrName;
    /**
     * 审核时间
     */
    private Long auditTime;
    /**
     * 资讯审核状态
     */
    private Integer auditStatus;
    /**
     * 审核者客户号
     */
    private Long auditCustId;
    /**
     * 上线时间
     */
    private Long onlineTime;
    /**
     * 资讯上线状态
     */
    private Integer onlineStatus;
    private Integer topperState;
    private Integer topperDay;
    private Long topperStartTime;
    private Integer views;
    private Long ownerId;
    /**
     * 应用ID
     */
    private Long appId;
    private Long createUser;// 创建用户
    private Long createTime;// 创建时间
    private Long modifyUser;// 更新用户
    private Long modifyTime;//更新时间 
    private Integer deleted;// 是否删除

    public Information(InformationAddDto obj) {
        setColumnType(obj.getColumnType());
        setColumnId(obj.getColumnId());
        setBizCode(obj.getBizCode());
        setAuthorCustId(obj.getAuthorCustId());
        setAuthorName(obj.getAuthorName());
        setAdcode(obj.getAdcode());
        setSource(obj.getSource());
        setSourceIcon(obj.getSourceIcon());
        setTitle(obj.getTitle());
        setSummary(obj.getSummary());
        //setTopicPhoto(obj.getTopicPhoto());
        //setVideo(obj.getVideo());
        setDuration(obj.getDuration());
        setContentType(obj.getContentType());
        setContent(obj.getContent());
        setAddrId(obj.getAddrId());
        setAddrName(obj.getAddrName());
        setAuditTime(obj.getAuditTime());
        setAuditStatus(obj.getAuditStatus());
        setAuditCustId(obj.getAuditCustId());
        setOnlineTime(obj.getOnlineTime());
        setOnlineStatus(obj.getOnlineStatus());
        setTopperState(obj.getTopperState());
        setTopperDay(obj.getTopperDay());
        setTopperStartTime(obj.getTopperStartTime());
        setViews(obj.getViews());
        setOwnerId(obj.getOwnerId());
        setAppId(obj.getAppId());
        setCreateUser(obj.getCreateUser());
        setCreateTime(obj.getCreateTime());
        setModifyUser(obj.getModifyUser());
        setModifyTime(obj.getModifyTime());
        setDeleted(obj.getDeleted());
    }
}