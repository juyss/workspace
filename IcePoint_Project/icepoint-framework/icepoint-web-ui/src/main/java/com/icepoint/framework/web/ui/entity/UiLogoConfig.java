package com.icepoint.framework.web.ui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * (UiLogoConfig)实体类
 *
 * @author makejava
 * @since 2021-06-18 15:30:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ui_logo_config")
public class UiLogoConfig extends LongStdEntity {

    /**
     * 显示logo。
     */
    @Column(name = "`is_show`")
    private Integer isShow;
    /**
     * 是否LOGO。占位时候，顶部的菜单色块或者底图会右移，不占位时候logo显示在色块或底图之上
     */
    @Column(name = "`logo`")
    private Integer logo;
    /**
     * 系统logo。
     */
    @Column(name = "`logo_pic`")
    private String logoPic;
    /**
     * 是否占位。
     */
    @Column(name = "`seat`")
    private Integer seat;
    /**
     * 端类型。PC、安卓、苹果、微信公众号、小程序，字典定义，可扩充
     */
    @Column(name = "`term_type`")
    private Integer termType;
    /**
     * 宽度。
     */
    @Column(name = "`width`")
    private String width;


}
