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
 * 轮播图
 *
 */
@Table
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Banner extends BasicEntity<Long> {
    /**
     * 业务码
     * 用于标识在应用中的不同位置
     */
    @Length(max = 16, message = "业务码长度不能超过16")
    private String bizCode;
    /**
     * 类型
     * 1-图片 2-视频 3-商品
     */
    private Integer bannerType;
    /**
     * 文件类型
     */
    private Integer fileType;
    /**
     * 文件地址
     */
    @Length(max = 128, message = "文件地址长度不能超过128")
    private String fileUrl;
    /**
     * 宽
     */
    private Integer width;
    /**
     * 高
     */
    private Integer height;
    /**
     * 文件大小
     */
    private Integer size;
    /**
     * 时长
     */
    private Integer duration;
    /**
     * 超链接
     */
    @Length(max = 128, message = "超链接长度不能超过128")
    private String hyperLink;
    /**
     * 业务对象ID
     */
    private Long objId;
    /**
     * 序号
     */
    private Integer idx;
    private Long ownerId;
    /**
     * 应用ID
     */
    private Long appId;
    /**
     * 创建者
     */
    private Long createUser;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 修改者
     */
    private Long modifyUser;
    /**
     * 修改时间
     */
    private Long modifyTime;
    private Integer deleted;

}