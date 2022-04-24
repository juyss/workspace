package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;

import javax.persistence.Column;

/**
 * 保养列表(WorkOrderTaskList)实体类
 *
 * @author makejava
 * @since 2021-07-22 15:19:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("work_order_task_list")
public class WorkOrderTaskList extends LongStdEntity {

    @Column(name = "`id`")
    private Long id;
    /**
     * 计划id
     */
    @Column(name = "`task_code`")
    private String taskCode;
    /**
     * 作业对象，该作业对象可以是作业任务中的对象，也可以是作业对象下面的具体对象，例如设备
     */
    @Column(name = "`work_obj_type`")
    private String workObjType;
    /**
     * 作业对象，该作业对象可以是作业任务中的对象，也可以是作业对象下面的具体对象，例如设备
     */
    @Column(name = "`work_obj`")
    private String workObj;
    /**
     * 作业对象，该作业对象可以是作业任务中的对象，也可以是作业对象下面的具体对象，例如设备
     */
    @Column(name = "`work_obj_name`")
    private String workObjName;
    /**
     * 保养描述
     */
    @Column(name = "`description`")
    private String description;
    /**
     * 保养时间
     */
    @Column(name = "`work_time`")
    private Long workTime;
    /**
     * 关联操作id，如果回退则为回退记录，如果是上报作业问题则为作业问题ID
     */
    @Column(name = "`operate_id`")
    private Long operateId;
    /**
     * 保养人
     */
    @Column(name = "`work_user_id`")
    private Long workUserId;



}
