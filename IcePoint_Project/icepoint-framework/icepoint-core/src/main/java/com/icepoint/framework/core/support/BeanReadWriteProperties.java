package com.icepoint.framework.core.support;

import com.icepoint.framework.core.util.PropertyDescriptorUtils;
import com.icepoint.framework.core.util.Streams;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public class BeanReadWriteProperties<T> implements ReadWriteProperties<T> {

    private final Map<String, PropertyDescriptor> propertyDescriptors;
    private final Class<T> beanClass;

    public BeanReadWriteProperties(Class<T> beanClass) {
        this.beanClass = beanClass;
        this.propertyDescriptors = Streams.stream(BeanUtils.getPropertyDescriptors(beanClass))
                .filter(pd -> !"class".equals(pd.getName()))
                .collect(Collectors.toMap(PropertyDescriptor::getName, pd -> pd));
    }

    @Override
    public @Nullable
    Object readProperty(T target, String propertyName) {
        PropertyDescriptor pd = propertyDescriptors.get(propertyName);
        return pd == null ? null : PropertyDescriptorUtils.invokeReadMethod(pd, target, false);
    }

    @Override
    public void writeProperty(T target, String propertyName, @Nullable Object value) {
        PropertyDescriptor pd = propertyDescriptors.get(propertyName);
        Assert.notNull(pd, String.format("该bean[%s]不存在该属性\"%s\"", beanClass, propertyName));
        PropertyDescriptorUtils.invokeWriteMethod(pd, target, value, false);
    }

    @Override
    public void forEach(T target, BiConsumer<String, Object> consumer) {
        propertyDescriptors.forEach((key, pd) -> consumer
                .accept(key, PropertyDescriptorUtils.invokeReadMethod(pd, target, false)));
    }
}
