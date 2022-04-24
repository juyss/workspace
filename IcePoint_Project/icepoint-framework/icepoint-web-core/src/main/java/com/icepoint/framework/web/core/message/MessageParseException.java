package com.icepoint.framework.web.core.message;

/**
 * 响应信息解析失败时抛出的异常
 *
 * @author Jiawei Zhao
 */
public class MessageParseException extends Exception {

    public MessageParseException(String message) {
        super(message);
    }

    public MessageParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageParseException(Throwable cause) {
        super(cause);
    }
}
