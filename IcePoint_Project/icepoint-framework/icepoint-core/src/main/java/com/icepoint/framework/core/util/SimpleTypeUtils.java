package com.icepoint.framework.core.util;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * 针对基本类型以及其包装类的共工具类
 *
 * @author Jiawei Zhao
 */
public final class SimpleTypeUtils {

    private static final Class<?>[] BOXED_TYPES = new Class[]{
            Byte.class, Short.class, Integer.class, Long.class,
            Float.class, Double.class,
            Boolean.class
    };

    private static final String PRIMITIVE_OBJECT_MUST_NOT_BE_NULL = MessageTemplates.notNull("基础类型对象");

    private SimpleTypeUtils() {
    }

    /**
     * 判断Class对象是否为基本类型或者基本类型包装类
     *
     * @param clazz 要检验的Class对象
     * @return 如果是基本类型或包装类返回true，否则返回false
     */
    public static boolean isPrimitiveOrBoxed(Class<?> clazz) {

        if (isPrimitive(clazz)) {
            return true;
        }

        return isBoxedType(clazz);
    }

    /**
     * 判断clazz对象是否基本类型，不包含包装类型
     *
     * @param clazz 要检验的Class对象
     * @return 如果是基本类型返回true，否则返回false
     */
    public static boolean isPrimitive(Class<?> clazz) {
        return clazz.isPrimitive();
    }

    /**
     * 判断clazz对象是否为包装类型，不包含String
     *
     * @param clazz 要检验的Class对象
     * @return 如果是包装类型返回true
     */
    public static boolean isBoxedType(Class<?> clazz) {

        for (Class<?> boxedType : BOXED_TYPES) {
            if (clazz == boxedType) {
                return true;
            }
        }

        return false;
    }

    public static boolean isByte(Class<?> clazz) {
        return clazz == byte.class;
    }

    public static boolean isBoxedByte(Class<?> clazz) {
        return clazz == Byte.class;
    }

    public static boolean isByteOrBoxed(Class<?> clazz) {
        return isByte(clazz) || isBoxedByte(clazz);
    }

    public static boolean isShort(Class<?> clazz) {
        return clazz == short.class;
    }

    public static boolean isBoxedShort(Class<?> clazz) {
        return clazz == Short.class;
    }

    public static boolean isShortOrBoxed(Class<?> clazz) {
        return isShort(clazz) || isBoxedShort(clazz);
    }

    public static boolean isInteger(Class<?> clazz) {
        return clazz == int.class;
    }

    public static boolean isBoxedInteger(Class<?> clazz) {
        return clazz == Integer.class;
    }

    public static boolean isIntegerOrBoxed(Class<?> clazz) {
        return isInteger(clazz) || isBoxedInteger(clazz);
    }

    public static boolean isLong(Class<?> clazz) {
        return clazz == long.class;
    }

    public static boolean isBoxedLong(Class<?> clazz) {
        return clazz == Long.class;
    }

    public static boolean isLongOrBoxed(Class<?> clazz) {
        return isLong(clazz) || isBoxedLong(clazz);
    }

    public static boolean isFloat(Class<?> clazz) {
        return clazz == float.class;
    }

    public static boolean isBoxedFloat(Class<?> clazz) {
        return clazz == Float.class;
    }

    public static boolean isFloatOrBoxed(Class<?> clazz) {
        return isFloat(clazz) || isBoxedFloat(clazz);
    }

    public static boolean isDouble(Class<?> clazz) {
        return clazz == double.class;
    }

    public static boolean isBoxedDouble(Class<?> clazz) {
        return clazz == Double.class;
    }

    public static boolean isDoubleOrBoxed(Class<?> clazz) {
        return isDouble(clazz) || isBoxedDouble(clazz);
    }

    public static boolean isBoolean(Class<?> clazz) {
        return clazz == boolean.class;
    }

    public static boolean isBoxedBoolean(Class<?> clazz) {
        return clazz == Boolean.class;
    }

    public static boolean isBooleanOrBoxed(Class<?> clazz) {
        return isBoolean(clazz) || isBoxedBoolean(clazz);
    }

    public static boolean isString(Class<?> clazz) {
        return clazz == String.class;
    }

    public static boolean isBigDecimal(Class<?> clazz) {
        return clazz == BigDecimal.class;
    }

    public static boolean isSimpleType(Class<?> clazz) {
        return isPrimitiveOrBoxed(clazz) || isString(clazz) || isBigDecimal(clazz);
    }

    /**
     * 将参数解析为byte，会先转为String再解析为byte，解析失败会抛出异常
     *
     * @param obj 要解析的对象
     * @return 解析完成的byte
     * @throws NumberFormatException 如果解析的对象不是可解析的byte
     */
    public static byte parseByte(Object obj) throws NumberFormatException {

        Assert.notNull(obj, PRIMITIVE_OBJECT_MUST_NOT_BE_NULL);

        return isBoxedByte(obj.getClass()) ? (Byte) obj : Byte.parseByte(parseString(obj));
    }

    /**
     * 将参数解析为short，会先转为String再解析为short，解析失败会抛出异常
     *
     * @param obj 要解析的对象
     * @return 解析完成的short
     * @throws NumberFormatException 如果解析的对象不是可解析的short
     */
    public static short parseShort(Object obj) throws NumberFormatException {

        Assert.notNull(obj, PRIMITIVE_OBJECT_MUST_NOT_BE_NULL);

        return isBoxedShort(obj.getClass()) ? (Short) obj : Short.parseShort(parseString(obj));
    }

    /**
     * 将参数解析为int，会先转为String再解析为int，解析失败会抛出异常
     *
     * @param obj 要解析的对象
     * @return 解析完成的int
     * @throws NumberFormatException 如果解析的对象不是可解析的int
     */
    public static int parseInt(Object obj) throws NumberFormatException {

        Assert.notNull(obj, PRIMITIVE_OBJECT_MUST_NOT_BE_NULL);

        return isBoxedInteger(obj.getClass()) ? (Integer) obj : Integer.parseInt(parseString(obj));
    }

    /**
     * 将参数解析为long，会先转为String再解析为long，解析失败会抛出异常
     *
     * @param obj 要解析的对象
     * @return 解析完成的long
     * @throws NumberFormatException 如果解析的对象不是可解析的long
     */
    public static long parseLong(Object obj) throws NumberFormatException {

        Assert.notNull(obj, PRIMITIVE_OBJECT_MUST_NOT_BE_NULL);

        return isBoxedLong(obj.getClass()) ? (Long) obj : Long.parseLong(parseString(obj));
    }

    /**
     * 将参数解析为float，会先转为String再解析为float，解析失败会抛出异常
     *
     * @param obj 要解析的对象
     * @return 解析完成的float
     * @throws NumberFormatException 如果解析的对象不是可解析的float
     */
    public static float parseFloat(Object obj) throws NumberFormatException {

        Assert.notNull(obj, PRIMITIVE_OBJECT_MUST_NOT_BE_NULL);

        return isBoxedFloat(obj.getClass()) ? (Float) obj : Float.parseFloat(parseString(obj));
    }

    /**
     * 将参数解析为double，会先转为String再解析为double，解析失败会抛出异常
     *
     * @param obj 要解析的对象
     * @return 解析完成的double
     * @throws NumberFormatException 如果解析的对象不是可解析的double
     */
    public static double parseDouble(Object obj) throws NumberFormatException {

        Assert.notNull(obj, PRIMITIVE_OBJECT_MUST_NOT_BE_NULL);

        return isBoxedDouble(obj.getClass()) ? (Double) obj : Double.parseDouble(parseString(obj));
    }

    /**
     * 将参数解析为boolean，会先转为String再解析为boolean，解析失败会抛出异常
     *
     * @param obj 要解析的对象
     * @return 解析完成的boolean
     * @throws NumberFormatException 如果解析的对象不是可解析的boolean
     */
    public static boolean parseBoolean(Object obj) {

        Assert.notNull(obj, PRIMITIVE_OBJECT_MUST_NOT_BE_NULL);

        return isBoxedBoolean(obj.getClass()) ? (Boolean) obj : BooleanUtils.toBoolean(parseString(obj));
    }

    public static String parseString(Object obj) {

        Assert.notNull(obj, PRIMITIVE_OBJECT_MUST_NOT_BE_NULL);

        return isString(obj.getClass()) ? (String) obj : String.valueOf(obj);
    }

    public static BigDecimal parseBigDecimal(Object obj) {

        Assert.notNull(obj, PRIMITIVE_OBJECT_MUST_NOT_BE_NULL);

        return isBigDecimal(obj.getClass()) ? (BigDecimal) obj : BigDecimal.valueOf(parseDouble(obj));
    }

    public static <T> T parse(Object obj, Class<T> type) {

        if (type.isAssignableFrom(obj.getClass())) {
            return CastUtils.cast(obj);
        }

        if (isSimpleType(type)) {
            if (isByteOrBoxed(type)) {
                return CastUtils.cast(parseByte(obj));
            } else if (isShortOrBoxed(type)) {
                return CastUtils.cast(parseShort(obj));
            } else if (isIntegerOrBoxed(type)) {
                return CastUtils.cast(parseInt(obj));
            } else if (isLongOrBoxed(type)) {
                return CastUtils.cast(parseLong(obj));
            } else if (isFloatOrBoxed(type)) {
                return CastUtils.cast(parseFloat(obj));
            } else if (isDoubleOrBoxed(type)) {
                return CastUtils.cast(parseDouble(obj));
            } else if (isBooleanOrBoxed(type)) {
                return CastUtils.cast(parseBoolean(obj));
            }else if (isString(type)) {
                return CastUtils.cast(String.valueOf(obj));
            } else if (isBigDecimal(type)) {
                return CastUtils.cast(parseBigDecimal(obj));
            }
        }

        throw new IllegalArgumentException("不支持的解析类型: " + type);
    }

    /**
     * 判断两个class对象是否属于同一种包装类型和基本类型，例如Integer和int会返回true
     *
     * @param clazz1 要检验的class1
     * @param clazz2 要检验的class2
     * @return 是同一种类型返沪true
     */
    public static boolean isSamePrimitiveOrBoxedType(Class<?> clazz1, Class<?> clazz2) { // NOSONAR

        if (!(isPrimitiveOrBoxed(clazz1) && isPrimitiveOrBoxed(clazz2))) {
            return false;
        } else if (clazz1 == clazz2) {
            return true;
        } else if (isPrimitive(clazz1) && isPrimitive(clazz2)) {
            return false;
        } else if (isBoxedType(clazz1) && isBoxedType(clazz2)) {
            return false;
        }

        Class<?> primitiveType;
        Class<?> boxedType;

        if (isPrimitive(clazz1)) {
            primitiveType = clazz1;
            boxedType = clazz2;
        } else {
            primitiveType = clazz2;
            boxedType = clazz1;
        }

        if (isByte(primitiveType)) {
            return isBoxedByte(boxedType);
        } else if (isShort(primitiveType)) {
            return isBoxedShort(boxedType);
        } else if (isInteger(primitiveType)) {
            return isBoxedInteger(boxedType);
        } else if (isLong(primitiveType)) {
            return isBoxedLong(boxedType);
        } else if (isFloat(primitiveType)) {
            return isBoxedFloat(boxedType);
        } else if (isDouble(primitiveType)) {
            return isBoxedDouble(boxedType);
        } else if (isBoolean(primitiveType)) {
            return isBoxedBoolean(boxedType);
        } else {
            return false;
        }
    }
}
