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
 * 标签定义表实体类
 *
 * @since 2021-07-07 10:09:55
 */
@Table(name = "sys_label_def")
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_label_def")
public class LabelDef extends LongStdEntity {
    /**
     * 标签总类
      */
    @Column(name = "`category`")
    private String category;

    /**
     *标签类型
     */
    @Column(name = "`type`")
    private String type;
    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;
    /**
     * 标签描述
     */
    @Column(name = "`describe`")
    private String describe;
    /**
     * 参数
     */
    @Column(name = "`param`")
    private String param;
    /**
     * 父id
     */
    @Column(name = "`parent_id`")
    private Long parentId;

}
