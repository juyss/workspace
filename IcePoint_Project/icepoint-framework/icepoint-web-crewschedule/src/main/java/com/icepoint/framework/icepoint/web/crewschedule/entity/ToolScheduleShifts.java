package com.icepoint.framework.icepoint.web.crewschedule.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * 排班班次关系表(SysToolScheduleShifts)实体类
 *
 * @author makejava
 * @since 2021-07-27 15:00:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_tool_schedule_shifts")
public class ToolScheduleShifts extends BaseEntity<Long> {

    /**
     * 排班表主键
     */
    @Column(name = "`schedule_id`")
    @TableField("`schedule_id`")
    private Long scheduleId;

    /**
     * 班次表主键
     */
    @Column(name = "`shifts_id`")
    @TableField("`shifts_id`")
    private Long shiftsId;

}
