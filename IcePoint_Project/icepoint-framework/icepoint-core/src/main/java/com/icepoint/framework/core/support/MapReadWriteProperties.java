package com.icepoint.framework.core.support;

import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Jiawei Zhao
 */
public class MapReadWriteProperties implements ReadWriteProperties<Map<String, Object>> {

    @Override
    public @Nullable Object readProperty(Map<String, Object> target, String propertyName) {
        return target.get(propertyName);
    }

    @Override
    public void writeProperty(Map<String, Object> target, String propertyName, @Nullable Object value) {
        target.put(propertyName, value);
    }

    @Override
    public void forEach(Map<String, Object> target, BiConsumer<String, Object> consumer) {
        target.forEach(consumer);
    }
}
