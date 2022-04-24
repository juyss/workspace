package com.icepoint.framework.icepoint.web.crewschedule.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * 班组表(SysToolGroup)实体类
 *
 * @author makejava
 * @since 2021-07-28 10:48:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_tool_group")
public class ToolGroup extends LongStdEntity {

    /**
     * 班组名称
     */
    @Column(name = "`name`")
    @TableField("`name`")
    private String name;

    /**
     * 关联部门ID
     */
    @Column(name = "`dept_id`")
    @TableField("`dept_id`")
    private Long deptId;

    /**
     * 关联角色ID
     */
    @Column(name = "`role_id`")
    @TableField("role_id")
    private Long roleId;

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
