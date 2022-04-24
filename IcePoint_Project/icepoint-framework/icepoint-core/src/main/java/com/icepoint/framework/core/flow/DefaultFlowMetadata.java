package com.icepoint.framework.core.flow;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jiawei Zhao
 */
public class DefaultFlowMetadata implements FlowMetadata {

    private final Map<Object, Object> data = new ConcurrentHashMap<>();

    @Override
    public ResultContainer getResultContainer() {
        return null;
    }

    @Override
    public Object get(Object key) {
        return data.get(key);
    }

    @Override
    public void set(Object key, Object value) {
        data.put(key, value);
    }
}
