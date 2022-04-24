package com.icepoint.framework.web.core.message;

import com.icepoint.framework.core.util.MessageTemplates;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * @author Jiawei Zhao
 */
public class CodedMessageException extends MessageException {

    private final String code;

    public CodedMessageException(String code) {
        this(code, null);
    }

    public CodedMessageException(String code, @Nullable Throwable cause) {
        super(null, cause);
        if (!StringUtils.hasText(code)) {
            throw new NullArgumentMessageException(MessageTemplates.notNull("code"));
        }
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
