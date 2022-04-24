package com.icepoint.framework.web.core.message;

import org.springframework.core.NestedExceptionUtils;

/**
 * @author Jiawei Zhao
 */
public class RootCauseMessageException extends ReasonableMessageException {

    public RootCauseMessageException(String code, Throwable cause) {
        super(code, NestedExceptionUtils.getMostSpecificCause(cause).getMessage(), cause);
    }
}
