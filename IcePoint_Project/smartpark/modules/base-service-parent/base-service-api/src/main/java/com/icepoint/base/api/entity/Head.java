package com.icepoint.base.api.entity;

import com.icepoint.base.api.domain.BasicEntity;
import com.icepoint.base.api.domain.GenericProperty;
import com.icepoint.base.api.supports.PropertyComparators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "t_tab_head")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Head extends BasicEntity<Long> implements GenericProperty {

    private Integer docType;//

    @Length(max = 100, message = "单据编号长度不能超过100")
    private String docNo;//

    @Length(max = 32, message = "字段名长度不能超过32")
    private String name;//

    private String value;//

    private Long ownerId;// 

    private Long appId;// 一个应用维护一个商品列表，免去商家各自维护

    private Long createTime;// 

    private Long createUser;// 

    private Long updateTime;//

    private Long updateUser;// 

    private Integer deleted;// 

    @Override
    public int compareTo(@Nullable GenericProperty o) {
        return PropertyComparators.VALUE.compare(this, o);
    }
}