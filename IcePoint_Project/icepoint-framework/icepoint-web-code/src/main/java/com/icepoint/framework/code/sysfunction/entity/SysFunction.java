package com.icepoint.framework.code.sysfunction.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.code.xml.entity.Process;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * (SysFunction)实体类
 *
 * @author makejava
 * @since 2021-06-07 10:05:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_function")
public class SysFunction extends LongStdEntity {

    /**
     * 建立工程的用户
     */
    @Column(name = "`cust_id`")
    private Long custId;
    /**
     * 函数类型  1-工程服务  2-函数  3-服务 4-流程函数
     */
    @Column(name = "`fun_type`")
    private Integer funType;
    /**
     * 名称,使用工程命名空间+模块名+服务名+方法名
     */
    @Column(name = "`name_en`")
    private String nameEn;
    /**
     * 中文简述
     */
    @Column(name = "`name`")
    private String name;
    /**
     * 函数说明
     */
    @Column(name = "`description`")
    private String description;
    /**
     * 函数图片
     */
    @Column(name = "`picture`")
    private String picture;
    /**
     * 服务HTPP地址
     */
    @Column(name = "`url`")
    private String url;
    /**
     * 服务的网络请求类型  GET POST PUT DELETE
     */
    @Column(name = "`http_model`")
    private Integer httpModel;
    /**
     * 服务、函数命名空间
     */
    @Column(name = "`service_ns`")
    private String serviceNs;
    /**
     * 所在工程ID
     */
    @Column(name = "`proj_id`")
    private Long projId;
    /**
     * sys_tab_service  ID
     */
    @Column(name = "`tab_service_id`")
    private Long tabServiceId;
    /**
     * 分组ID
     */
    @Column(name = "`group_id`")
    private Long groupId;

    @Column(name = "`idx`")
    private Integer idx;

    @Column(name = "`file_name`")
    private String fileName;
    /**
     * 状态，字典类型
     */
    @Column(name = "`status`")
    private Integer status;
    /**
     * 平台，用于SAAS产品，备用，当前应用中可以填固定值，要求查询时候作为必须条件字段
     */
    @Column(name = "`platform_id`")
    private Long platformId;

    @TableField(exist = false)
    private Process process;
}
