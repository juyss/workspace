package com.icepoint.framework.core.flow;

/**
 * @author Jiawei Zhao
 */
public interface FlowMetadata {

    ResultContainer getResultContainer();

    Object get(Object key);

    void set(Object key, Object value);
}
