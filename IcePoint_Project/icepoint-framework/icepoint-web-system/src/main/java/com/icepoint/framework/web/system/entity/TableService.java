package com.icepoint.framework.web.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.StdEntity;
import com.icepoint.framework.web.system.util.SystemConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * (SysTabService)表实体类
 *
 * @author makejava
 * @since 2021-06-04 16:12:27
 */
@TableName(value = "`sys_table_service`")
@Table(name = "`sys_table_service`")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class TableService extends StdEntity<Long, Long>  {

    //关联数据表ID
    @TableField("`table_id`")
    @Column(name = "`table_id`")
    private Long tableId;

    //服务名称
    @TableField("`name`")
    @Column(name = "`name`")
    private String name;

    //服务英文名称
    @TableField("`name_en`")
    @Column(name = "`name_en`")
    private String nameEn;

    //服务说明
    @TableField("`description`")
    @Column(name = "`description`")
    private String description;

    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`table_id`", insertable = false, updatable = false)
    private TableMetadata tableMetadata;

}
