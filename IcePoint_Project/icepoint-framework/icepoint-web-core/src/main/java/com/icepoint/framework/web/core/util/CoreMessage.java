package com.icepoint.framework.web.core.util;

import com.icepoint.framework.web.core.message.DataNotFoundMessageException;
import com.icepoint.framework.web.core.message.Message;
import com.icepoint.framework.web.core.message.UnknownMessageException;
import lombok.Getter;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * @author Jiawei Zhao
 */
@Getter
public enum CoreMessage implements Message {

    OK("200"),
    UNKNOWN("50002", UnknownMessageException.class),
    ILLEGAL_ARG("50000", IllegalArgumentException.class),
    NOT_FOUND("404", ResourceNotFoundException.class, DataNotFoundMessageException.class),
    OPERATE_FAILED("40001"),

    UNDEFINED("50004"),

    METHOD_NOT_SUPPORTED("50003", HttpRequestMethodNotSupportedException.class),

    ;

    private final String code;
    private final Class<?>[] exTypes;

    CoreMessage(String code, Class<?>... exTypes) {
        this.code = code;
        this.exTypes = exTypes;
    }


}
