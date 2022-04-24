package com.icepoint.framework.web.core.message;

import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public interface Message {

    String getCode();

    @Nullable
    Class<?>[] getExTypes();

    @Nullable
    default String getDefaultTemplate() {
        return null;
    }
}
