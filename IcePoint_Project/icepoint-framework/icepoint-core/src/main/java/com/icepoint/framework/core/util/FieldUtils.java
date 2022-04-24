package com.icepoint.framework.core.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 专门负责对反射类型{@link Field}进行各种操作的工具类
 *
 * @author Jiawei Zhao
 */
public final class FieldUtils {

    private static final Field[] EMPTY_FIELD_ARRAY = new Field[0];

    private static final Map<Class<?>, Field[]> declaredFieldsCache = new ConcurrentReferenceHashMap<>(256);

    private FieldUtils() {
    }

    @Nullable
    public static <T> T getField(Object target, String path) {

        String[] paths = path.split("\\.");

        Object result = target;
        for (String p : paths) {

            if (result == null) {
                return null;
            }

            p = p.trim();

            Field field = ReflectionUtils.findField(result.getClass(), p);
            if (field == null) {
                return null;
            }

            ReflectionUtils.makeAccessible(field);
            result = ReflectionUtils.getField(field, result);
        }

        return CastUtils.cast(result);
    }

    @Nullable
    public static <T> T getField(Field field, Object target) {
        ReflectionUtils.makeAccessible(field);
        return CastUtils.cast(ReflectionUtils.getField(field, target));
    }

    public static <T> T getRequiredField(Field field, Object target) {
        T result = getField(field, target);
        Assert.notNull(result, MessageTemplates.notNull("field"));
        return result;
    }

    public static void setField(Object target, String path, Object value) {

        String[] paths = path.split("\\.");

        Object finalTarget;
        String fieldName = paths[paths.length - 1];

        if (paths.length == 1) {
            finalTarget = target;
        } else {
            String[] targetPaths = new String[paths.length - 1];
            System.arraycopy(paths, 0, targetPaths, 0, paths.length - 1);
            finalTarget = getField(target, String.join(".", targetPaths));
        }

        Assert.notNull(finalTarget, "path中的field可能为空");

        Field field = ReflectionUtils.findField(finalTarget.getClass(), fieldName);
        Assert.notNull(field, "找不到对应的field: " + fieldName);

        int modifiers = field.getModifiers();
        if (!Modifier.isPublic(modifiers)) {
            ReflectionUtils.makeAccessible(field);
        }

        try {

            if (Modifier.isFinal(modifiers)) {

                Field ms = Field.class.getDeclaredField("modifiers");
                Assert.notNull(ms, "impossible");

                ReflectionUtils.makeAccessible(ms);
                ms.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            }

            field.set(finalTarget, value);

        } catch (Exception e) {
            ReflectionUtils.handleReflectionException(e);
        }
    }

    public static <T> T getRequiredField(Object target, String path) {

        T value = getField(target, path);

        Assert.notNull(value, "找不到属性或属性为空: " + path);

        return value;
    }

    public static Field[] findFields(Class<?> clazz) {
        return findFields(clazz, (Predicate<Field>) null);
    }

    public static Field[] findFields(Class<?> clazz, @Nullable Predicate<Field> filter) {

        Assert.notNull(clazz, MessageTemplates.notNull("Class"));

        Field[] result = new Field[0];

        Class<?> searchType = clazz;
        while (Object.class != searchType && searchType != null) {
            Field[] fields = getDeclaredFields(searchType);

            if (filter != null && fields.length != 0) {
                fields = Arrays.stream(fields)
                        .filter(filter)
                        .toArray(Field[]::new);
            }

            if (fields.length != 0) {
                result = ArrayUtils.addAll(result, fields);
            }

            searchType = searchType.getSuperclass();
        }

        return result;
    }

    @Nullable
    public static Field findFields(Class<?> clazz, String path) {

        String[] paths = path.split("\\.");

        Field result = null;
        Class<?> temp = clazz;
        for (String p : paths) {

            p = p.trim();

            result = ReflectionUtils.findField(temp, p);
            if (result == null) {
                return null;
            }

            temp = result.getType();
        }

        if (result == null) {
            return null;
        }

        ReflectionUtils.makeAccessible(result);
        return result;
    }

    public static Field[] getDeclaredFields(Class<?> clazz) {

        Assert.notNull(clazz, MessageTemplates.notNull("Class"));

        Field[] result = declaredFieldsCache.get(clazz);
        if (result == null) {
            try {
                result = clazz.getDeclaredFields();
                declaredFieldsCache.put(clazz, (result.length == 0 ? EMPTY_FIELD_ARRAY : result));
            } catch (Exception ex) {
                throw new IllegalStateException("Failed to introspect Class [" + clazz.getName() +
                        "] from ClassLoader [" + clazz.getClassLoader() + "]", ex);
            }
        }
        return result;
    }
}
