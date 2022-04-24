package com.icepoint.framework.web.system.model;

import lombok.*;

/**
 * @author ck
 * @version 1.0
 * @date 2021/5/25 9:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attr extends Column {
    /**
     * 是否必填
     */
    private Integer optional;
    /**
     * java类型
     */
    private String javaType;
    /**
     * 最大长度
     */
    private Integer maxlen;
    /**
     * 字段中文名
     */
    private String title;
    /**
     * 实体名
     */
    private String javaName;
    /**
     * 排序
     */
    private String des;
    /**
     * 字段名
     */
    private String jdbcName;

    /**
     * 是否是查询字段
     */
    private Integer queryField;

    /**
     * 最大值
     */
    private String maxVal;
    /**
     * 最小值
     */
    private String max;

    /**
     * 最小值
     */
    private String minVal;

    /**
     * 值域
     */
    private String domain;

}
