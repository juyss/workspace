package com.icepoint.framework.web.core.entity;

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
import javax.validation.constraints.NotEmpty;

/**
 * 数据字典
 *
 * @author Jiawei Zhao
 */
@Table(name = "sys_dict")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@TableName(value = "sys_dict")
public class Dict extends LongStdEntity implements ParentIdHierarchy<Long>, Ordered {


    /**
     * 字典分类
     */
    @NotEmpty
    @Column(name = "`category`")
    private String category;

    /**
     * 分类名称
     */
    @Column(name = "`category_name`")
    private String categoryName;

    /**
     * 字典项
     */
    @NotEmpty
    @Column(name = "`item`")
    private String item;

    /**
     * 字典项名称
     */
    @Column(name = "`item_name`")
    private String itemName;

    /**
     * 字典值
     */
    @NotEmpty
    @Column(name = "`value`")
    private String value;

    /**
     * 图标地址
     */
    @Column(name = "`icon`")
    private String icon;

    /**
     * 父级id
     */
    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 层级
     */
    @Column(name = "`level`")
    private Integer level;

    /**
     * 排序号
     */
    @Column(name = "`order`")
    @TableField(value = "`order`")
    private Integer order;

    /**
     * 备注
     */
    @Column(name = "`note`")
    private String note;

    @Override
    public int getOrder() {
        return OrderedUtils.nullable(this.order);
    }
}
