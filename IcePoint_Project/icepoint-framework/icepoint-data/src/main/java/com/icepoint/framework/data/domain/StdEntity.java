package com.icepoint.framework.data.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import static com.icepoint.framework.data.domain.PropertyConstants.*;

/**
 * 标准实体的父类
 *
 * @param <ID>  主键id的类型
 * @param <UID> 用户表主键id的类型
 * @author jiawei
 */
@JsonIgnoreProperties({ PLATFORM_ID, OWNER_ID, APP_ID })
@EntityListeners({ AuditingEntityListener.class, StdEntityEventListenerAdapter.class })
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public abstract class StdEntity<ID extends Serializable, UID extends Serializable>
        extends BaseEntity<ID> {

    /**
     * 平台，用于SAAS产品，备用，当前应用中可以填固定值，要求查询时候作为必须条件字段
     */
    @TableField(value = "`platform_id`")
    @Column(name = "`platform_id`")
    private Long platformId;

    @ApiModelProperty("应用id")
    @TableField(value = "`app_id`")
    @Column(name = "`app_id`")
    private Long appId;

    @ApiModelProperty("所有者id")
    @TableField(value = "`owner_id`")
    @Column(name = "`owner_id`")
    private Long ownerId;

    @CreatedBy
    @ApiModelProperty("创建用户")
    @TableField(value = "`create_user_id`")
    @Column(name = "`create_user_id`")
    private UID createUserId;

    @CreatedDate
    @ApiModelProperty("创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    @Column(name = "`create_time`")
    private Long createTime;

    @LastModifiedDate
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @Column(name = "`update_time`")
    private Long updateTime;

    @LastModifiedBy
    @ApiModelProperty("更新用户")
    @TableField("`update_user_id`")
    @Column(name = "`update_user_id`")
    private UID updateUserId;

    @ApiModelProperty("逻辑删除标识")
    @TableField(value = "`deleted`")
    @Column(name = "`deleted`")
    @ColumnDefault("0")
    @TableLogic
    private Boolean deleted;

}
