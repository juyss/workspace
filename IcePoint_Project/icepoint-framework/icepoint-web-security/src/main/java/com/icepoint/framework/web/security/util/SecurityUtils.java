package com.icepoint.framework.web.security.util;

import com.icepoint.framework.web.core.util.ServletUtils;
import com.icepoint.framework.web.security.entity.User;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
public class SecurityUtils {

    public static final String HEADER_APP_ID = "App-Id";
    public static final String HEADER_OWNER_ID = "Owner-Id";
    public static final String HEADER_PLATFORM_ID = "Platform-Id";

    public static final String DEFAULT_PASSWORD_TYPE = "1";

    public static final String LOGIN_BEFORE_THIS = "进行此操作必须先登录";

    public static String defaultPasswordType() {
        return DEFAULT_PASSWORD_TYPE;
    }

    private SecurityUtils() {}

    @Nullable
    public static Long getAppId() {
        return Optional.ofNullable(ServletUtils.getRequest())
                .map(request -> request.getHeader(HEADER_APP_ID))
                .map(Long::valueOf)
                .orElse(null);
    }

    @Nullable
    public static Long getOwnerId() {
        return Optional.ofNullable(ServletUtils.getRequest())
                .map(request -> request.getHeader(HEADER_OWNER_ID))
                .map(Long::valueOf)
                .orElse(null);
    }

    @Nullable
    public static Long getPlatformId() {
        return Optional.ofNullable(ServletUtils.getRequest())
                .map(request ->  request.getHeader(HEADER_PLATFORM_ID))
                .map(Long::valueOf)
                .orElse(null);
    }

    /**
     * 获取当前User对象，可能会获取不到
     *
     * @return 当前User对象，用Optional进行了包装
     */
    public static Optional<User> getUserOptional() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(User.class::isInstance)
                .map(User.class::cast);
    }

    /**
     * 返回当前User对象
     *
     * @return 当前User，没有登录的话返回null
     */
    @Nullable
    public static User getUser() {
        return getUserOptional().orElse(null);
    }

    /**
     * 获取当前User，不会为null
     *
     * @return 当前User
     * @throws IllegalStateException 当没有登录时
     */
    public static User getRequiredUser() throws IllegalStateException {
        return getUserOptional().orElseThrow(SecurityUtils::notLogin);
    }

    /**
     * 获取当前User的id
     *
     * @return 当前User的id，没有登录返回null
     */
    @Nullable
    public static Long getUserId() {
        return getUserOptional()
                .map(User::getId)
                .orElse(null);
    }

    /**
     * 获取当前User的id，不会返回null
     *
     * @return 当前User的id
     * @throws IllegalStateException 当没有登录时
     */
    public static Long getRequiredUserId() throws IllegalStateException {
        return getUserOptional()
                .map(User::getId)
                .orElseThrow(SecurityUtils::notLogin);
    }

    public static boolean isLegalPassword(String password) {
        return true;
    }

    private static RuntimeException notLogin() {
        return new InsufficientAuthenticationException(LOGIN_BEFORE_THIS);
    }
}
