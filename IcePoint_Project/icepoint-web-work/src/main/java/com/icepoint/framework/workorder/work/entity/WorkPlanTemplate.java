package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("work_order_work_template")
@Table(name = "work_order_work_template")
public class WorkPlanTemplate extends LongStdEntity {

    @Column(name = "`id`")
    @TableField("`id`")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 作业类型
     */
    @Column(name = "`type`")
    @TableField("`type`")
    private Integer type;

    /**
     * 模板代码
     */
    @Column(name = "`code`")
    @TableField("`code`")
    private String code;

    /**
     * 模板名称
     */
    @Column(name = "`name`")
    @TableField("`name`")
    private String name;

    /**
     * 备注
     */
    @Column(name = "`note`")
    @TableField("`note`")
    private String note;

    @TableField(exist = false)
    private List<WorkPlanTemplateDetail> workPlanTemplateDetails;


}
