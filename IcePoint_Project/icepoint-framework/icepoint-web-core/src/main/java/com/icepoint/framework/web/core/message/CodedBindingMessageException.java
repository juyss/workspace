package com.icepoint.framework.web.core.message;

/**
 * @author Jiawei Zhao
 */
public abstract class CodedBindingMessageException extends MessageException {

    protected CodedBindingMessageException() {
    }

    protected CodedBindingMessageException(Throwable cause) {
        super(cause);
    }
}
