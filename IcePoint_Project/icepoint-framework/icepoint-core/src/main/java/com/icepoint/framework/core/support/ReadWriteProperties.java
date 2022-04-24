package com.icepoint.framework.core.support;

import com.icepoint.framework.core.util.CastUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Jiawei Zhao
 */
public interface ReadWriteProperties<T> {

    /**
     * 读取属性
     *
     * @param target       要读取属性的目标对象
     * @param propertyName 读取的属性名
     * @return 属性值
     */
    @Nullable
    Object readProperty(T target, String propertyName);

    /**
     * 写入属性
     *
     * @param target       要写入的目标对象
     * @param propertyName 写入的属性名
     * @param value        属性值
     */
    void writeProperty(T target, String propertyName, @Nullable Object value);

    /**
     * 遍历所有属性
     *
     * @param target   要遍历的目标对象
     * @param consumer 遍历逻辑，第一个参数是属性名，第二个参数是属性值
     */
    void forEach(T target, BiConsumer<String, Object> consumer);

    /**
     * 根据目标类型创建{@link ReadWriteProperties}
     *
     * @param targetType 目标对象类型的Class对象
     * @param <T>        目标对象类型
     * @return ReadWriteProperties
     */
    static <T> ReadWriteProperties<T> of(Class<T> targetType) {

        if (Map.class.isAssignableFrom(targetType)) {
            return CastUtils.cast(new MapReadWriteProperties());
        } else {
            return new BeanReadWriteProperties<>(targetType);
        }
    }
}
