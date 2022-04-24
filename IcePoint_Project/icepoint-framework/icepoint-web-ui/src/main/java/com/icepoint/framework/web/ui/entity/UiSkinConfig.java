package com.icepoint.framework.web.ui.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * (UiSkinConfig)实体类
 *
 * @author makejava
 * @since 2021-06-18 16:31:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ui_skin_config")
public class UiSkinConfig extends LongStdEntity {
    private static final long serialVersionUID = 535866648362392870L;


    /**
     * PC、安卓、苹果、微信公众号、小程序，字典定义，可扩充
     */
    @Column(name = "`term_type`")
    private Object termType;
    /**
     * 颜色
     */
    @Column(name = "`color_skin`")
    private String colorSkin;
    /**
     *
     */
    @Column(name = "`pic_skin`")
    private String picSkin;



}
