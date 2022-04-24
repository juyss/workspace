package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.web.system.util.SystemConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jiawei Zhao
 */
@TableName(SystemConstants.GENERIC_DATA_TABLE_NAME)
@Table(name = SystemConstants.GENERIC_DATA_TABLE_NAME)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class GenericData extends LongStdEntity {

    /**
     * 对应的资源id
     */
    @Column(name = "`resource_id`")
    private Long resourceId;

    /**
     * 所属实体的数据标识
     */
    @Column(name = "`no`")
    private String no;

    /**
     * 是否大字符串类型值
     */
    @Column(name = "`big`")
    private Boolean big;

    /**
     * 字段名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 字段值
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 大字段值
     */
    @Column(name = "`big_value`")
    private String bigValue;

}
