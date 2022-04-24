package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.web.security.entity.User;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作业任务
 *
 * @author
 */
@Entity
@Table(name = "work_order_task")
@TableName("work_order_task")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkTask extends LongStdEntity {

    @Column(name = "id")
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 作业类型
     */
    @Column(name = "`work_type`")
    private Integer workType;

    /**
     * 任务编码
     */
    @Column(name = "`code`")
    private String code;

    /**
     * 任务名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 指派部门id
     */
    @Column(name = "`assigned_dept_id`")
    private Long assignedDeptId;

    /**
     * 指派人id
     */
    @Column(name = "`assigned_user_id`")
    private Long assignedUserId;

    /**
     * 开始时间
     */
    @Column(name = "`start_time`")
    private Long startTime;

    /**
     * 结束时间
     */
    @Column(name = "`end_time`")
    private Long endTime;

    /**
     * 计划下发时间
     */
    @Column(name = "`plan_time`")
    private Long planTime;

    /**
     * 实际下发时间
     */
    @Column(name = "`real_time`")
    private Long realTime;

    /**
     * 作业对象资产定义ID
     */
    @Column(name = "`work_obj_asset_def_id`")
    private Long workObjAssetDefId;

    /**
     * 作业对象
     */
    @Column(name = "`work_obj`")
    private Long workObj;

    /**
     * 作业对象名
     */
    @Column(name = "`work_obj_name`")
    private String workObjName;

    /**
     * 作业对象根节点资产定义ID
     */
    @Column(name = "`work_root_obj_asset_def_id`")
    private Long workRootObjAssetDefId;

    /**
     * 作业对象根节点
     */
    @Column(name = "`work_root_obj`")
    private Long workRootObj;

    /**
     * 作业对象根节点名
     */
    @Column(name = "`work_root_obj_name`")
    private String workRootObjName;

    /**
     * 作业内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 作业耗时
     */
    @Column(name = "`cost`")
    private Integer cost;

    /**
     * 注意事项
     */
    @Column(name = "`note`")
    private String note;

    /**
     * 作业组织
     */
    @Column(name = "`work_org`")
    private Long workOrg;

    /**
     * 作业组织根节点
     */
    @Column(name = "`work_root_org`")
    private Long workRootOrg;

    /**
     * 所属计划
     */
    @Column(name = "`plan_id`")
    private Long planId;

    /**
     * 任务状态
     */
    @Column(name = "`task_status`")
    private Integer taskStatus;

    /**
     * 作业模式
     */
    @Column(name = "`execute_mode`")
    private Integer executeMode;

    /**
     * 逾期状态
     */
    @Column(name = "`overdue`")
    private Integer overdue;

    /**
     * 完工时间
     */
    @Column(name = "`complete_time`")
    private Long completeTime;

    /**
     * 完结说明
     */
    @Column(name = "`complete_content`")
    private String completeContent;

    /**
     * 完工人
     */
    @Column(name = "`complete_user`")
    private Long completeUser;

    /**
     * 工作总结
     */
    @Column(name = "`work_summary`")
    private Long workSummary;

    /**
     * 催办时间
     */
    @Column(name = "`urging_time`")
    private Long urgingTime;



    @Column(name = "assets_id")
    private Long assetsId;


}
