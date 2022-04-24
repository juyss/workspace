package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongBaseEntity;
import com.icepoint.framework.web.system.util.SystemConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Jiawei Zhao
 */
@TableName(SystemConstants.GENERIC_DATA_TABLE_SEQUENCE_NAME)
@Table(name = SystemConstants.GENERIC_DATA_TABLE_SEQUENCE_NAME,
        uniqueConstraints = @UniqueConstraint(columnNames = { "resource_id", "object_id" }))
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class GenericDataSequence extends LongBaseEntity {

    @TableField("`resource_id`")
    @Column(name = "`resource_id`")
    private Long resourceId;

    @TableField("`object_id`")
    @Column(name = "`object_id`")
    private Long objectId;

    @TableField("`no`")
    @Column(name = "`no`")
    private String no;

    @TableField("`deleted`")
    @Column(name = "`deleted`")
    private Boolean deleted;

    /**
     * 是否已被使用
     */
    @TableField("`used`")
    @Column(name = "`used`")
    private Boolean used;


}
