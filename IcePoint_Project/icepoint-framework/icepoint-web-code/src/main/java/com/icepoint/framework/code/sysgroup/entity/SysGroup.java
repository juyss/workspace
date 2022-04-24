package com.icepoint.framework.code.sysgroup.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.Identifiable;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.data.domain.ParentIdHierarchy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * 分组表(SysGroup)实体类
 *
 * @author makejava
 * @since 2021-06-05 11:04:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_group")
public class SysGroup extends LongStdEntity implements Identifiable<Long>, ParentIdHierarchy<Long> {

    /**
     * 对象类型，流程工具中类型是用户
     */
    @Column(name = "`obj_type`")
    private Integer objType;

    /**
     * 对象ID，流程工具中是用户id，不同用户可以建立不同的分组
     */
    @Column(name = "`obj_id`")
    private Long objId;

    @Column(name = "`grp_type`")
    private Integer grpType;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`name_en`")
    private String nameEn;

    @Column(name = "`description`")
    private String description;

    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 排序
     */
    @Column(name = "`idx`")
    private Long idx;

    @Column(name = "`status`")
    private Integer status;

    /**
     * 平台，用于SAAS产品，备用，当前应用中可以填固定值，要求查询时候作为必须条件字段
     */
    @Column(name = "`platform_id`")
    private Long platformId;
}
