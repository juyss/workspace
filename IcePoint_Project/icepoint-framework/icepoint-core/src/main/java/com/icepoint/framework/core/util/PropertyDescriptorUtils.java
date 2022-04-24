package com.icepoint.framework.core.util;

import org.springframework.lang.Nullable;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jiawei Zhao
 */
public class PropertyDescriptorUtils {

    private PropertyDescriptorUtils() {
    }

    /**
     * 执行指定{@link PropertyDescriptor}的getter方法
     *
     * @param pd     PropertyDescriptor
     * @param target 要执行目标对象
     * @return 返回getter的返回值
     */
    @Nullable
    public static Object invokeReadMethod(PropertyDescriptor pd, Object target, boolean ignoreNotReadable) {

        Method readMethod = pd.getReadMethod();
        if (readMethod == null && ignoreNotReadable) {
            return null;
        } else if (readMethod == null) {
            throw new IllegalStateException(String.format("该属性\"%s\"不允许被读取", pd.getName()));
        }

        try {
            return readMethod.invoke(target);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(String.format("该属性\"%s\"的getter不是public", pd.getName()), e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(String.format("执行该属性\"%s\"的getter时抛出了一个异常", pd.getName()), e);
        }
    }

    /**
     * 执行指定{@link PropertyDescriptor}的setter方法
     *
     * @param pd     PropertyDescriptor
     * @param target 要执行目标对象
     * @param value  要写入的值，要就是执行setter的参数
     */
    public static void invokeWriteMethod(PropertyDescriptor pd, Object target, @Nullable Object value, boolean ignoreNotWritable) {

        Method writeMethod = pd.getWriteMethod();
        if (writeMethod == null && ignoreNotWritable) {
            return ;
        } else if (writeMethod == null) {
            throw new IllegalStateException(String.format("该属性\"%s\"不允许被写入", pd.getName()));
        }

        try {
            writeMethod.invoke(target, value);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(String.format("该属性\"%s\"的setter不是public", pd.getName()), e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(String.format("执行该属性\"%s\"的setter时抛出了一个异常", pd.getName()), e);
        }
    }

}
