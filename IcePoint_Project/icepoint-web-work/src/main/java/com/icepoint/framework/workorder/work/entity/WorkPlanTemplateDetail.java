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

import javax.lang.model.element.TypeElement;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Map;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("work_order_work_template_detail")
@Table(name = "work_order_work_template_detail")
public class WorkPlanTemplateDetail extends LongStdEntity {

    @Column(name = "id")
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Column(name = "`template_id`")
    @TableField("`template_id`")
    private Long templateId;

    @Column(name = "`work_obj`")
    @TableField("`work_obj`")
    private String workObj;

    @Column(name = "`work_root_obj`")
    @TableField("`work_root_obj`")
    private String workRootObj;

    @Column(name = "`start_time`")
    @TableField("`start_time`")
    private Long startTime;

    @Column(name = "`end_time`")
    @TableField("`end_time`")
    private Long endTime;

    @Column(name = "`send_time`")
    @TableField("`send_time`")
    private Long sendTime;

    @Column(name = "`content`")
    @TableField("`content`")
    private String content;

    @Column(name = "`cost`")
    @TableField("`cost`")
    private String cost;

    @Column(name = "`note`")
    @TableField("`note`")
    private String note;

    @Column(name = "work_obj_asset_def_id")
    private Long workObjAssetDefId;


}
