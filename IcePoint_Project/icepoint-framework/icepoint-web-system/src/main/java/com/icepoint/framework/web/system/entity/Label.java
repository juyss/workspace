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
 * 标签表实体类
 *
 * @since 2021-07-07 10:09:55
 */
@Table(name = "sys_label")
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_label")
public class Label extends LongStdEntity {

    @Column(name = "`table_id`")
    private Long tableId;

    @Column(name = "`obj_id`")
    private Long objId;

    @Column(name = "`label_id`")
    private Long labelId;
}
