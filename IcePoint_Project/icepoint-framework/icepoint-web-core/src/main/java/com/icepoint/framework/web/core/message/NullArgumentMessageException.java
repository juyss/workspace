package com.icepoint.framework.web.core.message;

import com.icepoint.framework.core.util.MessageTemplates;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * @author Jiawei Zhao
 */
public class NullArgumentMessageException extends MessageException {

    public NullArgumentMessageException(String nullTargetName) {
        this(nullTargetName, null);
    }

    public NullArgumentMessageException(String nullTargetName, @Nullable Throwable cause) {

        super(MessageTemplates.notNull(nullTargetName), cause);

        if (StringUtils.hasText(nullTargetName)) {
            throw new NullArgumentMessageException("nullTargetName", cause);
        }
    }
}
