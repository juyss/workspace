package com.github.tangyi.core.common.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 检查工具类
 *
 * @author hedongzhou
 * @date 2018/08/09
 */
public class CheckUtils {

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REG_EMAIL = "^([a-z0-9A-Z]+[_|\\-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证手机
     */
    public static final String REG_PHONE = "^\\d+(\\-)?\\d+$";

    /**
     * 正则表达式：验证国内手机
     */
    public static final String REG_CN_PHONE = "^((17)|(14)|(13)|(15)|(18))[0-9]\\d{8}$";

    /**
     * 全为空
     *
     * @param objs
     * @return
     */
    public static boolean isAllEmpty(Object... objs) {
        if (isEmpty(objs)) {
            return true;
        }

        for (Object one : objs) {
            if (isNotEmpty(one)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 只要有一个对象为空，则返回true
     *
     * @param objs
     * @return
     */
    public static boolean isAnyEmpty(Object... objs) {
        if (isEmpty(objs)) {
            return true;
        }

        for (Object one : objs) {
            if (isEmpty(one)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 所有的对象都不为空
     *
     * @param objs
     * @return
     */
    public static boolean isNoOneEmpty(Object... objs) {
        return !isAnyEmpty(objs);
    }

    /**
     * 判断是否为空
     *
     * @param objs
     * @return
     */
    public static boolean isEmpty(Object[] objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否不为空
     *
     * @param objs
     * @return
     */
    public static boolean isNotEmpty(Object[] objs) {
        return !isEmpty(objs);
    }

    /**
     * 判断是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && StringUtils.isBlank((String) obj)) {
            return true;
        } else if (obj instanceof Collection && CollectionUtils.isEmpty((Collection<?>) obj)) {
            return true;
        } else if (obj instanceof Map && CollectionUtils.isEmpty((Map<?, ?>) obj)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否不为空
     *
     * @param <T>
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 是否为邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REG_EMAIL, email);
    }

    /**
     * 是否为电话号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        return Pattern.matches(REG_PHONE, phone);
    }

    /**
     * 是否为国内电话号码
     *
     * @param phone
     * @return
     */
    public static boolean isCnPhone(String phone) {
        return Pattern.matches(REG_CN_PHONE, phone);
    }

    /**
     * 只要等于其中一个，返回true
     *
     * @param obj
     * @param values
     * @return
     */
    public static boolean in(Object obj,
                             Collection<?> values) {
        return in(obj, values.toArray());
    }

    /**
     * 只要等于其中一个，返回true
     *
     * @param obj
     * @param values
     * @return
     */
    public static boolean in(Object obj,
                             Object... values) {
        if (CheckUtils.isEmpty(obj) ||
                CheckUtils.isEmpty(values)) {
            return false;
        }

        for (Object value : values) {
            if (obj.equals(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 全部不等于，返回true
     *
     * @param obj
     * @param values
     * @return
     */
    public static boolean notIn(Object obj,
                                Collection<?> values) {
        return notIn(obj, values.toArray());
    }

    /**
     * 全部不等于，返回true
     *
     * @param obj
     * @param values
     * @return
     */
    public static boolean notIn(Object obj,
                                Object... values) {
        if (CheckUtils.isEmpty(obj)) {
            return false;
        }
        if (CheckUtils.isEmpty(values)) {
            return true;
        }

        for (Object value : values) {
            if (obj.equals(value)) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }
}
