package com.icepoint.framework.web.ui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * (UiMenuConfig)实体类
 *
 * @author makejava
 * @since 2021-06-17 20:29:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ui_menu_config")
public class UiMenuConfig extends LongStdEntity {

    /**
     * 配置。鉴于各端可能属性不一样，为便于扩展和调整，采用统一方法存储
     */
    @Column(name = "`config`")
    private String config;

    @Column(name = "`note`")
    private String note;
    /**
     * 端类型。PC、安卓、苹果、微信公众号、小程序，字典定义，可扩充
     */
    @Column(name = "`termtype`")
    private Integer termtype;



}
