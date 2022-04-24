package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 对象属性表(sys_attribute)实体类
 *
 * @since 2021-07-07 10:00:55
 */
@Table(name = "sys_attribute")
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_attribute")
public class Attribute extends BaseEntity<Long> {

    /**
     * 对象类型
     */
    @Column(name = "`obj_type`")
    private Long objType;

    /**
     * 所属对象ID
     */
    @Column(name = "`obj_id`")
    private Long objId;

    /**
     * 所属表元数据ID
     */
    @Column(name = "`table_id`")
    private Long tableId;

    /**
     * 扩展字段名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 扩展字段值
     */
    @Column(name = "`value`")
    private String value;

    @Column(name = "`group_id`")
    private Long groupId;

    @Column(name = "`attr_idx`")
    private Integer attrIdx;

    @Column(name = "`status`")
    private Integer status;
}
