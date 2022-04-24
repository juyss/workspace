package com.icepoint.framework.web.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.core.util.OrderedUtils;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.data.domain.ParentIdHierarchy;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.core.Ordered;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
@Table(name = "auth_role")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@TableName("auth_role")
public class Role extends LongStdEntity implements ParentIdHierarchy<Long> {

    @Column(name = "`parent_id`")
    @TableField("`parent_id`")
    private Long parentId;

    @Column(name = "`name`")
    @TableField("`name`")
    private String name;

    @Column(name = "`code`")
    @TableField("`code`")
    private String code;

    @Column(name = "`description`")
    @TableField("`description`")
    private String description;

    @Column(name = "`order`")
    @TableField("`order`")
    private Integer order;

    @Transient
//    @ManyToMany(mappedBy = "roles")
    @TableField(exist = false)
    private List<Organization> organizations;

//    @Override
//    public int getOrder() {
//        return OrderedUtils.nullable(this.order);
//    }
}
