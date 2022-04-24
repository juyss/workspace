package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 对象多属性表(multi_attribute)实体类
 *
 * @since 2021-07-07 10:05:55
 */
@Table(name = "multi_attribute")
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("multi_attribute")
public class MultiAttribute extends LongStdEntity {

    @Column(name = "`obj_type`")
    private Integer objType;

    @Column(name = "`obj_id`")
    private Long objId;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`value`")
    private String value;

    @Column(name = "`multi_idx`")
    private Integer multiIdx;

    @Column(name = "`attr_idx`")
    private Integer attrIdx;

    @Column(name = "`status`")
    private Integer status;

    @Column(name = "`group_id`")
    private Long groupId;
}
