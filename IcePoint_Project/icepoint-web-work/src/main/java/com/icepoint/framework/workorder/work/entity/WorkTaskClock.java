package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

/**
 * 作业任务打卡
 *
 * @author
 */
@Entity
@Table(name = "work_order_task_clock")
@TableName("work_order_task_clock")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkTaskClock extends LongStdEntity {

    @Column(name = "id")
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 作业任务
     */
    @Column(name = "`task_id`")
    @TableField("`task_id`")
    private Long taskId;

    /**
     * 打卡点
     * 编码
     */
    @Column(name = "`points`")
    private String points;

    /**
     * 位置描述
     */
    @Column(name = "`place`")
    private String place;

    /**
     * 打卡状态
     */
    @Column(name = "`clock_status`")
    private Integer clockStatus;

    /**
     * 打卡时间
     */
    @Column(name = "`clock_time`")
    private Long clockTime;

    /**
     * 打卡人
     */
    @Column(name = "`clock_user_id`")
    private Long clockUserId;

    @TableField("`task_code`")
    @Column(name = "`task_code`")
    private String taskCode;

}
