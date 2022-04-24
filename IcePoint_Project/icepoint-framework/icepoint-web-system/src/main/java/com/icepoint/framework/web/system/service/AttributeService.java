package com.icepoint.framework.web.system.service;

import com.icepoint.framework.web.system.entity.Attribute;
import com.icepoint.framework.web.system.entity.Label;
import com.icepoint.framework.web.system.entity.MultiAttribute;

import java.util.List;

/**
 * 对象属性管理服务接口
 *
 * @since 2021-07-07 10:14:58
 */
public interface AttributeService {
    /**
     * 对象属性表批量添加
     *
     * @param attrs 对象属性数组
     * @return 插入个数
     */
    Integer addAttrs(List<Attribute> attrs);

    /**
     * 对象多属性表批量添加
     *
     * @param multsAttrs 对象多属性数组
     * @return 插入个数
     */
    Integer addMultiAttrs(List<MultiAttribute> multsAttrs);

    /**
     * 对象标签表批量添加
     *
     * @param labels 对象标签数组
     * @return 插入个数
     */
    Integer addObjLabels(List<Label> labels);

}
