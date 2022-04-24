package com.icepoint.framework.web.system.resource.query;

/**
 * @author Jiawei Zhao
 */
public enum Operation {

    /**
     * 等于
     */
    EQ,

    /**
     * 不等于
     */
    NE,

    /**
     * 大于
     */
    GT,

    /**
     * 大于等于
     */
    GE,

    /**
     * 小于
     */
    LT,

    /**
     * 小于等于
     */
    LE,

    /**
     * 多个值任意一个等于
     */
    IN,

    /**
     * 多个值全部不等于
     */
    NOT_IN,

    /**
     * 区间
     */
    BETWEEN,

    /**
     * 字符串包含
     */
    LIKE
}
