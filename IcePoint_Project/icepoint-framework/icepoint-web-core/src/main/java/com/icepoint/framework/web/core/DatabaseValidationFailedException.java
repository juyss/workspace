package com.icepoint.framework.web.core;

/**
 * @author Jiawei Zhao
 */
public class DatabaseValidationFailedException extends Exception {

    public DatabaseValidationFailedException() {
    }

    public DatabaseValidationFailedException(String message) {
        super(message);
    }

    public DatabaseValidationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseValidationFailedException(Throwable cause) {
        super(cause);
    }

    public DatabaseValidationFailedException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
