package com.icepoint.framework.web.core.message;

import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public interface MessageContext {

    String getCode(Exception ex);

    String getMessage(@Nullable Exception ex, String code);

    String getMessage(String code);

    String getMessage(Message message);
}
