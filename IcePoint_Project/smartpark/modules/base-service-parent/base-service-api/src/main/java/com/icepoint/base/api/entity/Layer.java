package com.icepoint.base.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 图层
 * @author BD
 *
 */
@Table(name = "t_res_layer")
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Layer extends BasicEntity<Long> implements Ordered {

    @NotEmpty(message = "资源ID不能为空")
    private Long resId;//

    private Integer idx;//

    private Integer show;//

    private String config;//

    private Long ownerId;// 

    private Long appId;// 一个应用维护一个商品列表，免去商家各自维护

    private Long createTime;// 

    private Long createUser;// 

    private Long updateTime;// 

    private Long updateUser;// 

    private Integer deleted;//

    @JsonIgnore
    @Override
    public int getOrder() {
        return getIdx();
    }
}