package com.github.tangyi.core.common.util;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * 数字工具
 *
 * @author hedongzhou
 */
public class MathUtils {

    /**
     * 数字格式化
     */
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("0.00");

    /**
     * 金额格式化
     */
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("###,##0.00");

    /**
     * 是否为整数
     *
     * @param value
     * @return
     */
    public static boolean isInt(Object value) {
        return getDouble(value) == getInt(value);
    }

    /**
     * 获取int
     *
     * @param value
     * @return
     */
    public static int getInt(Object value) {
        if (CheckUtils.isNotEmpty(value)) {
            if (Number.class.isAssignableFrom(value.getClass())) {
                return ((Number) value).intValue();
            } else {
                return Integer.valueOf(String.valueOf(value));
            }
        }

        return 0;
    }

    /**
     * 获取long
     *
     * @param value
     * @return
     */
    public static long getLong(Object value) {
        if (CheckUtils.isNotEmpty(value)) {
            if (Number.class.isAssignableFrom(value.getClass())) {
                return ((Number) value).longValue();
            } else {
                return Long.valueOf(String.valueOf(value));
            }
        }

        return 0L;
    }

    /**
     * 获取double
     *
     * @param value
     * @return
     */
    public static double getDouble(Object value) {
        if (CheckUtils.isNotEmpty(value)) {
            if (Number.class.isAssignableFrom(value.getClass())) {
                return ((Number) value).doubleValue();
            } else {
                return Double.valueOf(String.valueOf(value));
            }
        }

        return 0;
    }

    /**
     * 获取BigDecimal
     *
     * @param value
     * @return
     */
    public static BigDecimal getBigDecimal(Object value) {
        if (CheckUtils.isNotEmpty(value)) {
            if (Number.class.isAssignableFrom(value.getClass())) {
                return BigDecimal.valueOf(((Number) value).doubleValue());
            } else {
                return new BigDecimal(String.valueOf(value));
            }
        }

        return BigDecimal.ZERO;
    }

    /**
     * 设置两位小数位
     *
     * @param value
     * @return
     */
    public static BigDecimal setScale(Object value) {
        return setScale(value, 2);
    }

    /**
     * 设置小数位
     *
     * @param value
     * @param newScale
     * @return
     */
    public static BigDecimal setScale(Object value, int newScale) {
        return getBigDecimal(value).setScale(newScale, BigDecimal.ROUND_UP);
    }

    /**
     * 设置小数位
     *
     * @param value
     * @return
     */
    public static Double ceil(Object value) {
        return Math.ceil(getDouble(value));
    }

    /**
     * 范围
     *
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static boolean between(Object value, Object min, Object max) {
        BigDecimal v = getBigDecimal(value);
        return v.compareTo(getBigDecimal(min)) >= 0 && v.compareTo(getBigDecimal(max)) <= 0;
    }

    /**
     * 获取数字格式字符串
     *
     * @param value
     * @return
     */
    public static String formatNumber(Object value) {
        return NUMBER_FORMAT.format(getBigDecimal(value));
    }

    /**
     * 获取金额格式字符串
     *
     * @param value
     * @return
     */
    public static String formatMoney(Object value) {
        return MONEY_FORMAT.format(getBigDecimal(value));
    }

    /**
     * 获取N个随机数
     *
     * @param size
     * @param start
     * @param end
     * @return
     */
    public static Set<Integer> randomInt(int size,
                                         int start,
                                         int end) {
        Set<Integer> set = new HashSet<>();
        while (set.size() < size) {
            set.add(RandomUtils.nextInt(start, end + 1));
        }
        return set;
    }

}
