package com.icepoint.framework.web.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.web.system.util.SystemConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
@Table(name = "sys_table_metadata")
@TableName("sys_table_metadata")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class TableMetadata extends LongStdEntity {

    /**
     * 关联的资源id
     */
    @TableField("`resource_id`")
    @Column(name = "`resource_id`")
    private Long resourceId;

    /**
     * 模块id
     */
    @TableField("`module_id`")
    @Column(name = "`module_id`")
    private Long moduleId;

    /**
     * 1-数据库表  2-服务
     */
    @TableField("`tab_type`")
    @Column(name = "`tab_type`")
    private Integer tabType;

    /**
     * 名称
     */
    @TableField("`name`")
    @Column(name = "`name`")
    private String name;

    /**
     * 英文名称
     */
    @TableField("`name_en`")
    @Column(name = "`name_en`")
    private String nameEn;

    /**
     * 类型，由具体应用自己决定，字典类型
     */
    @TableField("`biz_type`")
    @Column(name = "`biz_type`")
    private Integer bizType;

    /**
     * 服务路径
     */
    @TableField("`service_url`")
    @Column(name = "`service_url`")
    private String serviceUrl;

    /**
     * 1-GET 2-POST 3-PUT
     */
    @TableField("`service_type`")
    @Column(name = "`service_type`")
    private Integer serviceType;

    /**
     * 请求参数
     */
    @TableField("`req_Param`")
    @Column(name = "`req_Param`")
    private String reqParam;

    /**
     * 状态，字典类型
     */
    @TableField("`status`")
    @Column(name = "`status`")
    private Integer status;

    /**
     * 排序号
     */
    @TableField("`order`")
    @Column(name = "`order`")
    private Integer order;

    /**
     * 表说明
     */
    @TableField("`description`")
    @Column(name = "`description`")
    private String description;

    /**
     * 关联的资源
     */
    @TableField(exist = false)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`resource_id`", insertable = false, updatable = false)
    private Resource resource;

    /**
     * 关联的模块
     */
    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`module_id`", insertable = false, updatable = false)
    private Module module;

    /**
     * 关联的表服务
     */
    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @OneToOne(mappedBy = "tableMetadata", fetch = FetchType.LAZY)
    private TableService tableService;

    /**
     * 关联的字段元数据
     */
    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @OneToMany(mappedBy = "tableMetadata", fetch = FetchType.LAZY)
    private List<FieldMetadata> fieldMetadatas;

}
