package com.icepoint.framework.web.security.util;

import lombok.Data;

import java.util.function.LongConsumer;

/**
 * @author Jiawei Zhao
 */
@Data
public class SecurityProperties {

    private Long ownerId;

    private Long appId;

    private Long platformId;

    public void processOwnerIdIfNonnull(LongConsumer ownerIdConsumer) {
        if (ownerId != null) {
            ownerIdConsumer.accept(ownerId);
        }
    }

    public void processAppIdIfNonnull(LongConsumer appIdConsumer) {
        if (appId != null) {
            appIdConsumer.accept(appId);
        }
    }

    public void processPlatformIdIfNonnull(LongConsumer platformIdConsumer) {
        if (platformId != null) {
            platformIdConsumer.accept(platformId);
        }
    }
}
