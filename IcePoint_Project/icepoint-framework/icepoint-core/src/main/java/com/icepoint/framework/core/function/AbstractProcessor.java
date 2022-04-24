package com.icepoint.framework.core.function;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractProcessor<P, R> extends ProcessorSupports implements Processor<P> {

    @Override
    public final Map<String, Object> process(P parameters) throws IllegalArgumentException {
        R result = processInternal(parameters);
        return wrapResult(result);
    }

    protected abstract R processInternal(P parameters);

    protected abstract Map<String, Object> wrapResult(R result);
}
