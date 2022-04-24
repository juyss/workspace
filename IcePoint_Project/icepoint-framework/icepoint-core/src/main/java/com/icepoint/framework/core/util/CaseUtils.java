package com.icepoint.framework.core.util;

import com.google.common.base.CaseFormat;

/**
 * 各种大小写，命名规则的转换工具类
 *
 * @author Jiawei Zhao
 */
public final class CaseUtils {

    private CaseUtils() {
    }

    /**
     * 小驼峰转大驼峰
     *
     * @param str 字符串
     * @return 转换结果
     */
    public static String capitalize(String str) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, str);
    }

    /**
     * 大驼峰转小驼峰
     *
     * @param str 字符串
     * @return 转换结果
     */
    public static String uncapitalize(String str) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, str);
    }

    /**
     * 驼峰转小写下划线
     *
     * @param str 字符串
     * @return 转换结果
     */
    public static String toLowerUnderScore(String str) {
        return Character.isUpperCase(str.charAt(0))
                ? CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str)
                : CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }

    /**
     * 驼峰转大写下划线
     *
     * @param str 字符串
     * @return 转换结果
     */
    public static String toUpperUnderScore(String str) {
        return Character.isUpperCase(str.charAt(0))
                ? CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, str)
                : CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, str);
    }

    /**
     * 下划线转大驼峰
     *
     * @return 转换结果
     */
    public static String toUpperCamel(String str) {
        return Character.isUpperCase(str.charAt(0))
                ? CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str)
                : CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str);
    }

    /**
     * 下划线转小驼峰
     *
     * @return 转换结果
     */
    public static String toLowerCamel(String str) {
        return Character.isUpperCase(str.charAt(0))
                ? CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str)
                : CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }
}
