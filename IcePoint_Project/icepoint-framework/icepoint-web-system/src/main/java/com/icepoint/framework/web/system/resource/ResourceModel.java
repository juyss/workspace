package com.icepoint.framework.web.system.resource;

import com.icepoint.framework.data.domain.Identifiable;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Jiawei Zhao
 */
public interface ResourceModel extends Identifiable<Long> {

    Lookup getLookup();

    void setProperty(String name, @Nullable Object value);

    void setProperties(Map<String, Object> properties);

    @Nullable
    Object getProperty(String name);

    @Nullable <T> T getProperty(String name, Class<T> type);

    boolean hasProperty(String name);

    String[] getPropertyNames();

    void forEach(BiConsumer<String, Object> consumer);

    boolean isEmpty();
}
