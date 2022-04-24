package com.icepoint.framework.core.function;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public interface Processor<P> {

    Map<String, Object> process(P parameters) throws IllegalArgumentException;
}
