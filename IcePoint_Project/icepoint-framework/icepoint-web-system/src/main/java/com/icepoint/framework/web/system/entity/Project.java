package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.StdEntity;
import com.icepoint.framework.web.system.util.SystemConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * (SysProject)表实体类
 *
 * @author makejava
 * @since 2021-05-24 16:57:35
 */
@TableName("sys_project")
@Table(name = "sys_project")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Project extends StdEntity<Long, Long> {

    //编号
    @TableField("`serial_no`")
    @Column(name = "`serial_no`")
    private String serialNo;

    //名称
    @TableField("`name`")
    @Column(name = "`name`")
    private String name;

    //英文名称
    @TableField("`name_en`")
    @Column(name = "`name_en`")
    private String nameEn;

    //类型，由具体应用自己决定，字典类型
    @TableField("`biz_type`")
    @Column(name = "`biz_type`")
    private Integer bizType;

    //项目图标
    @TableField("`icon`")
    @Column(name = "`icon`")
    private String icon;

    //项目概述
    @TableField("`description`")
    @Column(name = "`description`")
    private String description;

    //所有者名称
    @TableField("`master_id`")
    @Column(name = "`master_id`")
    private Long masterId;

    //所有者名称
    @TableField("`master_name`")
    @Column(name = "`master_name`")
    private String masterName;

    //作者ID
    @TableField("`author_id`")
    @Column(name = "`author_id`")
    private Long authorId;

    //作者名称
    @TableField("`author_name`")
    @Column(name = "`author_name`")
    private String authorName;

    //所在行业
    @TableField("`industry`")
    @Column(name = "`industry`")
    private Integer industry;

    //开发语言，针对软件有效  ，字典类型
    @TableField("`develop_language`")
    @Column(name = "`develop_language`")
    private Integer developLanguage;

    //服务端框架
    @TableField("`server_framework`")
    @Column(name = "`server_framework`")
    private Integer serverFramework;

    //页面端框架
    @TableField("`client_framework`")
    @Column(name = "`client_framework`")
    private Integer clientFramework;

    //数据库类型
    @TableField("`db_type`")
    @Column(name = "`db_type`")
    private Integer dbType;

    @TableField("`cust_id`")
    @Column(name = "`cust_id`")
    private Long custId;

    @TableField("`orm`")
    @Column(name = "`orm`")
    private Integer orm;

    //测试框架
    @TableField("`test_framework`")
    @Column(name = "`test_framework`")
    private Integer testFramework;

    //档类型
    @TableField("`doc_type`")
    @Column(name = "`doc_type`")
    private Integer docType;

    //项目状态，字典类型
    @TableField("`status`")
    @Column(name = "`status`")
    private Integer status;

    //是否生成工程代码框架
    @TableField("`generate`")
    @Column(name = "`generate`")
    private Integer generate;

    @TableField("`node_icon`")
    @Column(name = "`node_icon`")
    private String nodeIcon;

    @TableField("`namespace`")
    @Column(name = "`namespace`")
    private String namespace;

    @TableField("`access_key_id`")
    @Column(name = "`access_key_id`")
    private String accessKeyId;

    @TableField("`access_key_secret`")
    @Column(name = "`access_key_secret`")
    private String accessKeySecret;

    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Resource> resources;

    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Module> modules;
}
