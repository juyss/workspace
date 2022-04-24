package com.icepoint.framework.code.sysfunservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * (SysFunService)实体类
 *
 * @author ck
 * @since 2021-06-04 17:44:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_fun_service")
public class SysFunService extends LongStdEntity {

    /**
     * 工程ID
     */
    @Column(name = "`group_id`")
    private Long groupId;
    /**
     * 流程英文名称
     */
    @Column(name = "`name`")
    private String name;
    /**
     * 流程显示名称
     */
    @Column(name = "`title`")
    private String title;
    /**
     * 流程说明
     */
    @Column(name = "`description`")
    private String description;
    /**
     * 生成服务时的服务名
     */
    @Column(name = "`serv_name`")
    private String servName;
    /**
     * 生成服务时的方法名
     */
    @Column(name = "`method_name`")
    private String methodName;
    /**
     * 所在模块名,英文,无空格
     */
    @Column(name = "`module`")
    private String module;
    /**
     * 1-GET 2-POST 3-PUT 4-DELETE
     */
    @Column(name = "`http_mode`")
    private Integer httpMode;
    /**
     * 是否发布为函数
     */
    @Column(name = "`as_fun`")
    private Integer asFun;
    /**
     * 发布为函数所在组ID
     */
    @Column(name = "`fun_group_id`")
    private Long funGroupId;
    /**
     * 是否生成工程代码
     */
    @Column(name = "`generated`")
    @TableField(value = "`generated`")
    private Integer generated;

    /**
     * 保存JSON字符串
     */
    @Column(name = "json_flow")
    private String jsonFlow;

    /**
     * 保存xml文件路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 状态 1-有效 0-删除
     */
    @Column(name = "`status`")
    private Integer status;
    /**
     * 平台，用于SAAS产品，备用，当前应用中可以填固定值，要求查询时候作为必须条件字段
     */
    @Column(name = "`platform_id`")
    private Long platformId;
    /**
     * 所有者，应用的所有者，业务查询中必须字段，当前使用中可以填固定值
     */
    @Column(name = "`owner_id`")
    private Long ownerId;
    /**
     * 应用编号，所有者所申请的应用，一般会对应一对AppID、AppSecret，当前应用中可以填固定值
     */
    @Column(name = "`app_id`")
    private Long appId;



}
