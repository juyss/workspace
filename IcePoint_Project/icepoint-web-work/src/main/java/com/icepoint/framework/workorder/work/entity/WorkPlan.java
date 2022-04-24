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
import java.util.List;
import java.util.Map;

/**
 * 作业计划
 *
 * @author Jiawei Zhao
 */
@Entity
@Table(name = "work_order_work_plan")
@TableName("work_order_work_plan")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkPlan extends LongStdEntity {

    @Column(name = "id")
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 作业模版id
     */
    @Column(name = "`template_id`")
    private Long templateId;

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
     * 作业类型
     */
    @Column(name = "`work_type`")
    private Integer workType;

    /**
     * 计划编码
     */
    @Column(name = "`code`")
    private String code;

    /**
     * 计划名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 周期类型
     */
    @Column(name = "`cycle_type`")
    private Integer cycleType;

    /**
     * 第一次任务开始时间
     */
    @Column(name = "`first_time`")
    private Long firstTime;

    /**
     * 上次计划执行时间
     */
    @Column(name = "`last_time`")
    private Long lastTime;

    /**
     * 每期任务生成时间，T+N分钟
     */
    @Column(name = "`generate_time`")
    private Long generateTime;

    /**
     * 注意事项
     */
    @Column(name = "`note`")
    private String note;

    /**
     * 计划状态  未启用 已启用 已停用  1，2，3
     */
    @Column(name = "`plan_status`")
    private Integer planStatus;

    /**
     * 作业模式 1-有一人完成即可 2-多人都完成方可
     */
    @Column(name = "`execute_mode`")
    private Integer executeMode;

    /**
     * 逾期时效
     */
    @Column(name = "`overdue_aging`")
    private String overdueAging;

    /**
     * 是否自动派工
     */
    @Column(name = "`auto_dispatch`")
    @TableField("`auto_dispatch`")
    private Boolean autoDispatch;
}
