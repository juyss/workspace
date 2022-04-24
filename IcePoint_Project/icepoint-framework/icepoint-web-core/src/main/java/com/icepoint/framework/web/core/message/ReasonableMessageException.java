package com.icepoint.framework.web.core.message;

import org.jetbrains.annotations.Nullable;

/**
 * @author Jiawei Zhao
 */
public class ReasonableMessageException extends CodedMessageException {

    private final String reason;

    public ReasonableMessageException(String code, String reason) {
        this(code, reason, null);
    }

    public ReasonableMessageException(String code, String reason, @Nullable Throwable cause) {
        super(code, cause);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
