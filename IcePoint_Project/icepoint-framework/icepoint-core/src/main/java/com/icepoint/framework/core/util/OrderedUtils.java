package com.icepoint.framework.core.util;

import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;

/**
 * 基于{@link Ordered}接口的工具类
 *
 * @author Jiawei Zhao
 */
public final class OrderedUtils {

    private OrderedUtils() {
    }

    /**
     * 允许为空的排序号获取int类型排序号，如果为空则会返回最低排序号: {@link Ordered#LOWEST_PRECEDENCE}
     *
     * @param order Integer类型的排序号
     * @return int类型的排序号
     */
    public static int nullable(@Nullable Integer order) {
        return nullLow(order);
    }

    /**
     * 允许为空的排序号获取int类型排序号，如果为空则会返回最高排序号: {@link Ordered#HIGHEST_PRECEDENCE}
     *
     * @param order Integer类型的排序号
     * @return int类型的排序号
     */
    public static int nullHigh(@Nullable Integer order) {
        return order == null ? Ordered.HIGHEST_PRECEDENCE : order;
    }

    /**
     * 允许为空的排序号获取int类型排序号，如果为空则会返回最低排序号: {@link Ordered#LOWEST_PRECEDENCE}
     *
     * @param order Integer类型的排序号
     * @return int类型的排序号
     */
    public static int nullLow(@Nullable Integer order) {
        return order == null ? Ordered.LOWEST_PRECEDENCE : order;
    }
}
