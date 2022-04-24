package com.icepoint.framework.web.core.util;

import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.web.core.message.CodedAssertMessageException;
import com.icepoint.framework.web.core.message.Message;
import org.jetbrains.annotations.Contract;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Jiawei Zhao
 */
public class MessageAssert {

    private MessageAssert() {
        throw new UnsupportedOperationException();
    }

    @Contract("null -> fail")
    public static void notNull(@Nullable Object obj) {
        notNull(obj, null);
    }

    @Contract("null, _ -> fail")
    public static void notNull(@Nullable Object obj, @Nullable String code) {
        isTrue(obj != null, code);
    }

    @Contract("null -> fail")
    public static void notEmpty(@Nullable Object[] array) {
        notEmpty(array, null);
    }

    @Contract("null, _ -> fail")
    public static void notEmpty(@Nullable Object[] array, @Nullable String code) {
        isTrue(array != null && array.length > 0, code);
    }

    @Contract("null -> fail")
    public static void hasText(@Nullable String str) {
        hasText(str, null);
    }

    @Contract("null, _ -> fail")
    public static void hasText(@Nullable String str, @Nullable String code) {
        isTrue(StringUtils.hasText(str), (String) null);
    }

    @Contract("false -> fail")
    public static void isTrue(boolean bool) {
        isTrue(bool, (String) null);
    }

    @Contract("false, _ -> fail")
    public static void isTrue(boolean bool, @Nullable String code) {
        if (!bool) {
            if (!StringUtils.hasText(code)) {
                code = CoreMessage.ILLEGAL_ARG.getCode();
            }
            throw new CodedAssertMessageException(code);
        }
    }

    @Contract("false, _ -> fail")
    public static void isTrue(boolean bool, Message message) {
        Assert.notNull(message, MessageTemplates.notNull("message"));
        isTrue(bool, message.getCode());
    }

    @Contract("true -> fail")
    public static void isFalse(boolean bool) {
        isTrue(!bool, (String) null);
    }

    @Contract("true, _ -> fail")
    public static void isFalse(boolean bool, @Nullable String code) {
        isTrue(!bool, code);
    }
}
