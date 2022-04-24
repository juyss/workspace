package com.icepoint.framework.web.ui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * (UiMenuTemplate)实体类
 *
 * @author makejava
 * @since 2021-06-17 21:14:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ui_menu_template")
public class UiMenuTemplate extends LongStdEntity {
    /**
     * 背景颜色。
     */
    @Column(name = "`bgcolor`")
    private String bgcolor;
    /**
     * 模板代码。
     */
    @Column(name = "`code`")
    private String code;
    /**
     * 字体颜色
     */
    @Column(name = "`color_text`")
    private String colorText;

    @Column(name = "`css`")
    private String css;
    /**
     * 自定义。
     */
    @Column(name = "`custom`")
    private Integer custom;
    /**
     * 模板名称。
     */
    @Column(name = "`name`")
    private String name;
    /**
     * 端类型。PC、安卓、苹果、微信公众号、小程序，字典定义，可扩充
     */
    @Column(name = "`term_type`")
    private Integer termType;
    /**
     * 换肤配置,JSON对象
     */
    @Column(name = "`skin`")
    private String skin;


}
