package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.core.util.OrderedUtils;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.data.domain.ParentIdHierarchy;
import com.icepoint.framework.web.system.util.SystemConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.springframework.core.Ordered;

import javax.persistence.*;

/**
 * @author Jiawei Zhao
 */
@TableName("sys_resource")
@Table(name = "sys_resource")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Resource extends LongStdEntity implements ParentIdHierarchy<Long>, Ordered {

    /**
     * 关联的项目id
     */
    @Column(name = "`project_id`")
    private Long projectId;

    /**
     * 父id
     */
    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 别名
     */
    @Column(name = "`name_alias`")
    private String nameAlias;

    /**
     * 编码
     */
    @Column(name = "`code`")
    private String code;

    /**
     * 资源类型
     */
    @Column(name = "`type`")
    private String type;

    /**
     * 排序号
     */
    @Column(name = "`idx`")
    private Integer idx;

    /**
     * 描述
     */
    @Column(name = "`description`")
    private String description;

    /**
     * 父资源
     */
    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`parent_id`", insertable = false, updatable = false)
    private Resource parent;
//
    /**
     * 关联的表元数据
     */
    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @OneToOne(mappedBy = "resource", fetch = FetchType.LAZY)
    private TableMetadata tableMetadata;

    /**
     * 关联的项目
     */
    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`project_id`", insertable = false, updatable = false)
    private Project project;

    @Override
    public int getOrder() {
        return OrderedUtils.nullable(this.idx);
    }
}
