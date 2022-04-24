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
public class Role extends LongStdEntity implements ParentIdHierarchy<Long>, Ordered {

    @Column(name = "`parent_id`")
    private Long parentId;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`code`")
    private String code;

    @Column(name = "`description`")
    private String description;

    @Column(name = "`order`")
    private Integer order;

    @Transient
//    @ManyToMany(mappedBy = "roles")
    private List<Dept> organizations;

    @Override
    public int getOrder() {
        return OrderedUtils.nullable(this.order);
    }
}
