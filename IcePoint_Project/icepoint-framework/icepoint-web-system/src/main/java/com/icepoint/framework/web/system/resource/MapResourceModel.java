package com.icepoint.framework.web.system.resource;

import org.springframework.lang.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Jiawei Zhao
 */
public class MapResourceModel extends AbstractResourceModel {

    private final Map<String, Object> map;

    public MapResourceModel(Lookup lookup) {
        super(lookup);
        this.map = new LinkedHashMap<>();
    }

    @Override
    public void setProperty(String name, @Nullable Object value) {
        map.put(name, value);
    }

    @Nullable
    @Override
    public Object getProperty(String name) {
        return map.get(name);
    }

    @Override
    public boolean hasProperty(String name) {
        return map.containsKey(name);
    }

    @Override
    public String[] getPropertyNames() {
        return map.keySet().toArray(new String[0]);
    }

    @Override
    public void forEach(BiConsumer<String, Object> consumer) {
        map.forEach(consumer);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

}
