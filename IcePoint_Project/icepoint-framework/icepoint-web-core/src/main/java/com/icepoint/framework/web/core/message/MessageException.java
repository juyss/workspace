package com.icepoint.framework.web.core.message;

import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public abstract class MessageException extends RuntimeException {

    protected MessageException() {
    }

    protected MessageException(@Nullable String message) {
        super(message);
    }

    protected MessageException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    protected MessageException(@Nullable Throwable cause) {
        super(cause);
    }
}
