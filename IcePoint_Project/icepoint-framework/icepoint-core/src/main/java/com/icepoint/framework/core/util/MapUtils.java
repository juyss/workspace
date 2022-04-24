package com.icepoint.framework.core.util;

import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * 针对Map进行各种处理的工具类
 *
 * @author Jiawei Zhao
 */
public final class MapUtils {

    private MapUtils() {
    }

    /**
     * 返回处理key重复的{@link BiFunction}对象，主要用于map的merge方法
     *
     * @return BiFunction
     */
    public static <T, U, R> BiFunction<T, U, R> duplicateKeys() {
        return (a, b) -> {
            throw new IllegalArgumentException(String.format("插入了重复的键: %s", a));
        };
    }

    /**
     * 对象转map，驼峰命名转换下划线
     *
     * @param obj 对象
     * @return map
     */
    public static Map<String, Object> objectToLineMap(Object obj) {
        Map<String, Object> map = new HashMap<>();

        if (ObjectUtils.isEmpty(obj)) {
            return map;
        }

        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            ReflectionUtils.makeAccessible(field);
            String fieldName = field.getName();
            String line = CaseUtils.toLowerUnderScore(fieldName);
            Object value = ReflectionUtils.getField(field, obj);
            if (value != null) {
                map.put(line, value);
            }
        }
        return map;
    }

    /**
     * 对象转map，驼峰命名不转换
     *
     * @param obj 对象
     * @return map
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            ReflectionUtils.makeAccessible(field);
            String fieldName = field.getName();
            Object value = ReflectionUtils.getField(field, obj);
            if (value != null) {
                map.put(fieldName, value);
            }
        }
        return map;
    }

}
