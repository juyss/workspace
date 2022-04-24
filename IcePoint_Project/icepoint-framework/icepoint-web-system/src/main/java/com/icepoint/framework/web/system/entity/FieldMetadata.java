package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.web.system.util.SystemConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author Jiawei Zhao
 */
@TableName("sys_field_metadata")
@Table(name = "sys_field_metadata")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class FieldMetadata extends LongStdEntity {

    /**
     * 表格元数据id
     */
    @TableField()
    @Column(name = "`table_id`")
    private Long tableId;

    /**
     * 字段名称
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
     * 显示名称
     */
    @TableField("`display_name`")
    @Column(name = "`display_name`")
    private String displayName;

    /**
     * 储存的英文名
     */
    @TableField("`store_name_en`")
    @Column(name = "`store_name_en`")
    private String storeNameEn;

    /**
     * 字段java类型
     */
    @TableField("`java_type`")
    @Column(name = "`java_type`")
    private String javaType;

    /**
     * 字段数据库类型
     */
    @TableField("`native_type`")
    @Column(name = "`native_type`")
    private String nativeType;

    /**
     * 是否主键
     */
    @TableField("`primary_key`")
    @Column(name = "`primary_key`")
    private Boolean primaryKey;

    /**
     * 是否逻辑删除标识字段（应该限制必须是布尔类型字段）
     */
    @TableField("`delete_marker`")
    @Column(name = "`delete_marker`")
    private Boolean deleteMarker;

    /**
     * 长度
     */
    @TableField("`max_length`")
    @Column(name = "`max_length`")
    private Integer maxLength;

    /**
     * 最大值
     */
    @TableField("`max`")
    @Column(name = "`max`")
    private String max;

    /**
     * 最小值
     */
    @TableField("`min`")
    @Column(name = "`min`")
    private String  min;

    /**
     * 小数位数
     */
    @TableField("`fractional`")
    @Column(name = "`fractional`")
    private Integer fractional;

    /**
     * 是否必填
     */
    @TableField("`optional`")
    @Column(name = "`optional`")
    private Boolean optional;

    /**
     * 缺省值
     */
    @TableField("`default_value`")
    @Column(name = "`default_value`")
    private String defaultValue;

    /**
     * 值域
     */
    @TableField()
    @Column(name = "`domain`")
    private String domain;

    /**
     * 是否可编辑
     */
    @TableField("`editable`")
    @Column(name = "`editable`")
    private Boolean editable;

    /**
     * 是否可排序
     */
    @TableField("`sortable`")
    @Column(name = "`sortable`")
    private Boolean sortable;

    /**
     * 是否可查询
     */
    @TableField("`queryable`")
    @Column(name = "`queryable`")
    private Boolean queryable;

    /**
     * 是否列表字段，非列表字段仅在详情查询时返回，在列表查询中不返回
     */
    @TableField("`list_field`")
    @Column(name = "`list_field`")
    private Boolean listField;

    /**
     * 是否字典字段
     */
    @TableField("`dict_field`")
    @Column(name = "`dict_field`")
    private Boolean dictField;

    /**
     * 字典分类
     */
    @TableField("`dict_category`")
    @Column(name = "`dict_category`")
    private Integer dictCategory;

    /**
     * 界面元素类型
     */
    @TableField("`ui_Type`")
    @Column(name = "`ui_Type`")
    private String uiType;

    @TableField("`biz_type`")
    @Column(name = "`biz_type`")
    private Integer bizType;

    /**
     * 状态，字典类型
     */
    @TableField("`status`")
    @Column(name = "`status`")
    private Integer status;

    /**
     * 字段说明
     */
    @TableField("`description`")
    @Column(name = "`description`")
    private String description;

    /**
     * 排序号
     */
    @TableField("`order`")
    @Column(name = "`order`")
    private Integer order;

    /**
     * 字段类型  1多属性  2多属性 0 非扩展字段
     */
    @TableField("`type`")
    @Column(name = "`type`")
    private Integer type;


    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`table_id`", insertable = false, updatable = false)
    private TableMetadata tableMetadata;
}
