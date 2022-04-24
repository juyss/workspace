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
 * 人员班次关系表(SysToolUserShifts)实体类
 *
 * @author makejava
 * @since 2021-07-27 15:00:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_tool_user_shifts")
public class ToolUserShifts extends BaseEntity<Long> {

    /**
     * 人员表主键
     */
    @Column(name = "`userid`")
    @TableField("`userid`")
    private Long userid;

    /**
     * 班次表主键
     */
    @Column(name = "`shifts_id`")
    @TableField("`shifts_id`")
    private Long shiftsId;

    /**
     * 值班日期
     */
    @Column(name = "`workday`")
    @TableField("`workday`")
    private Long workday;


}
