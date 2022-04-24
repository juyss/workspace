package com.icepoint.framework.web.core.message;

/**
 * 数据没有找到时抛出
 *
 * @author Jiawei Zhao
 */
public class DataNotFoundMessageException extends CodedBindingMessageException {

    public DataNotFoundMessageException() {
    }

    public DataNotFoundMessageException(Throwable cause) {
        super(cause);
    }
}

