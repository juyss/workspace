package com.icepoint.framework.web.security.util;

import com.icepoint.framework.web.core.message.Message;
import lombok.Getter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;

/**
 * @author Jiawei Zhao
 */
@Getter
public enum SecurityMessage implements Message {

    LOGIN_FAILED_WRONG("40101", BadCredentialsException.class),
    NOT_LOGIN("40300", InsufficientAuthenticationException.class),
    NOT_AUTHORIZED("403"),

    USERNAME_NOT_EXISTS("40102", UsernameNotFoundException.class),
    USER_LOCKED("40103", LockedException.class),
    USER_EXPIRED("40104", AccountExpiredException.class),
    USER_DISABLED("40105", DisabledException.class),
    CERTIFICATION_EXPIRED("40106", CredentialsExpiredException.class),
    OPERATE_NOT_AUTHORIZED("40107", AccessDeniedException.class),
    CSRF_TOKEN_INVALID("40108", InvalidCsrfTokenException.class),

    USERNAME_ALREADY_EXISTED("50005"),

    ILLEGAL_PASSWORD("50006");

    private final String code;
    private final Class<?>[] exTypes;

    SecurityMessage(String code, Class<?>... exTypes) {
        this.code = code;
        this.exTypes = exTypes;
    }
}
