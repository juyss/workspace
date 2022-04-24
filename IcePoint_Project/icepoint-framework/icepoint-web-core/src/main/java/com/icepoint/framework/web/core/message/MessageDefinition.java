package com.icepoint.framework.web.core.message;

import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class MessageDefinition {

    private final String defaultTemplate;

    private final String code;

    private final List<Class<? extends Exception>> exceptionTypes;

    public MessageDefinition(@Nullable String defaultTemplate, String code,
            @Nullable List<Class<? extends Exception>> exceptionTypes) {

        this.defaultTemplate = defaultTemplate;
        this.code = code;
        this.exceptionTypes = exceptionTypes == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(exceptionTypes);
    }

    @Nullable
    public String getDefaultTemplate() {
        return defaultTemplate;
    }

    public String getCode() {
        return code;
    }

    public List<Class<? extends Exception>> getExceptionTypes() {
        return exceptionTypes;
    }
}
