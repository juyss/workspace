package com.icepoint.framework.web.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对应pdm文件中各个节点 解析为列
 *
 * @author ck
 * @version 1.0
 * @date 2021/5/19 11:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 字段名字(中文)
     */
    private String name;
    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段本来类型
     */
    private String nativeType;

    /**
     * 字段名字(英文)
     */
    private String nameEn;
    /**
     * 长度
     */
    private Integer maxlen;
    /**
     * 是否为主键
     */
    private boolean primaryKey;
    /**
     * 是否外键
     */
    private String pkField;
    /**
     * 最大值
     */
    private String maxVal;

    /**
     * 最小值
     */
    private String minVal;

    /**
     * 小数点后几位
     */
    private String fractional;


    /**
     * 是否必填 1为必填
     */
    private String uniqueidx;
}
