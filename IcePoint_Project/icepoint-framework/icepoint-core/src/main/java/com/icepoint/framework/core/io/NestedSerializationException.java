package com.icepoint.framework.core.io;

import org.springframework.core.NestedRuntimeException;

/**
 * @author Jiawei Zhao
 */
public class NestedSerializationException extends NestedRuntimeException {

    public NestedSerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
