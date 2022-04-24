package com.icepoint.framework.icepoint.web.crewschedule.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * 排班表(SysToolSchedule)实体类
 *
 * @author makejava
 * @since 2021-07-27 14:55:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_tool_schedule")
public class ToolSchedule extends LongStdEntity {

    /**
     * 人员id
     */
    @Column(name = "`user_id`")
    @TableField("`user_id`")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 关联班次id
     */
    @Column(name = "`tool_shifts_id`")
    @TableField("`tool_shifts_id`")
    @NotNull(message = "班次idID不能为空")
    private Long toolShiftsId;

    /**
     * 值班日期
     */
    @Column(name = "`work_day`")
    @TableField("`work_day`")
    @NotNull(message = "值班日期不能为空")
    private Date workDay;

    /**
     * 关联的班次信息
     */
    @Transient
    @TableField(exist = false)
    private ToolShifts toolShifts;
}
