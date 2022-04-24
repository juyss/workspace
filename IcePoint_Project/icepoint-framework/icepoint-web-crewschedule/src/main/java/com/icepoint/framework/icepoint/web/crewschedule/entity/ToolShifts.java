package com.icepoint.framework.icepoint.web.crewschedule.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Time;

/**
 * 班次表(SysToolShifts)实体类
 *
 * @author makejava
 * @since 2021-07-27 14:52:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_tool_shifts")
public class ToolShifts extends LongStdEntity {

    /**
     * 班次名称
     */
    @Column(name = "`name`")
    @TableField("`name`")
    private String name;

    /**
     * 班次组别
     */
    @Column(name = "`group_id`")
    @TableField("`group_id`")
    private Long groupId;

    /**
     * 班次开始时间
     */
    @Column(name = "`start_time`")
    @TableField("`start_time`")
    private Time startTime;

    /**
     * 班次结束时间
     */
    @Column(name = "`end_time`")
    @TableField("`end_time`")
    private Time endTime;

    /**
     * 颜色编码
     */
    @Column(name = "`color`")
    @TableField("`color`")
    private String color;

    /**
     * 排序号
     */
    @Column(name = "`idx`")
    @TableField("`idx`")
    private Integer idx;

    /**
     * 备注
     */
    @Column(name = "`note`")
    @TableField("`note`")
    private String note;
}
