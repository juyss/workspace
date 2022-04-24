package com.icepoint.framework.web.core.message;

/**
 * @author Jiawei Zhao
 */
public class UnknownMessageException extends CodedBindingMessageException {

    public UnknownMessageException() {
    }

    public UnknownMessageException(Throwable cause) {
        super(cause);
    }
}
