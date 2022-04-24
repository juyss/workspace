package com.icepoint.framework.web.security.util;

import org.jetbrains.annotations.Nullable;

/**
 * @author Jiawei Zhao
 */
public class SecurityPropertiesFetcher {

    private SecurityPropertiesFetcher() {
    }

    /**
     * 从当前请求获取{@link SecurityProperties}
     *
     * @return SecurityProperties
     */
    public static SecurityProperties fetchFromRequest() {
        return getSecurityProperties(
                SecurityUtils.getAppId(),
                SecurityUtils.getOwnerId(),
                SecurityUtils.getPlatformId());
    }

    /**
     * 从当前登录用户获取{@link SecurityProperties}
     *
     * @return SecurityProperties
     */
    public static SecurityProperties fetchFromSignedUser() {

        return SecurityUtils.getUserOptional()
                .map(user -> getSecurityProperties(
                        user.getAppId(),
                        user.getOwnerId(),
                        user.getPlatformId()
                ))
                .orElseGet(SecurityProperties::new);
    }

    private static SecurityProperties getSecurityProperties(@Nullable Long appId, @Nullable Long ownerId,
            @Nullable Long platformId) {

        SecurityProperties properties = new SecurityProperties();
        properties.setAppId(appId);
        properties.setOwnerId(ownerId);
        properties.setPlatformId(platformId);
        return properties;
    }
}
