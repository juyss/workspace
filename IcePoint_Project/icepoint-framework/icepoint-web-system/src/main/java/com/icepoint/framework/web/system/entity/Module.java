package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.core.util.OrderedUtils;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.web.system.util.SystemConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.springframework.core.Ordered;

import javax.persistence.*;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
@TableName("sys_module")
@Table(name = "sys_module")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Module extends LongStdEntity implements Ordered {

    /**
     * 关联的项目id
     */
    @TableField("project_id")
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 名称
     */
    @TableField("`name`")
    @Column(name = "`name`")
    private String name;

    /**
     * 别名
     */
    @TableField("`alias`")
    @Column(name = "`alias`")
    private String alias;

    /**
     * 排序号
     */
    @TableField("`order`")
    @Column(name = "`order`")
    private Integer order;

    /**
     * 关联的项目实体
     */
    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private Project project;

    @TableField(exist = false)
    @Where(clause = SystemConstants.NOT_DELETED)
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private List<TableMetadata> tableMetadatas;

    @Override
    public int getOrder() {
        return OrderedUtils.nullable(this.order);
    }
}
