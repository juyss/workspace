package com.icepoint.framework.plugin.pmi;

import com.icepoint.framework.core.util.OrderedUtils;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.data.domain.ParentIdHierarchy;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.core.Ordered;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "pmi_dept")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Dept extends LongStdEntity implements ParentIdHierarchy<Long>, Ordered {
    /**
     * 部门类型
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 部门名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 部门编码
     */
    @Column(name = "`code`")
    private String code;

    /**
     * 上级部门ID
     */
    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 部门负责人ID
     */
    @Column(name = "`director`")
    private Long director;

    /**
     * 序号
     */
    @Column(name = "`idx`")
    private Integer idx;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    /**
     * 如果有关联对象，其在资产定义表中的ID
     */
    @Column(name = "`asset_def_id`")
    private Long assetDefId;

    /**
     * 如果有关联对象，对象ID
     */
    @Column(name = "`obj_id`")
    private Long objId;

    @Override
    public int getOrder() {
        return OrderedUtils.nullable(this.idx);
    }
}
