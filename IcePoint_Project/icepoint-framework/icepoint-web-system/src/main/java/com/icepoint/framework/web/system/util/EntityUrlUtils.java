package com.icepoint.framework.web.system.util;

import com.icepoint.framework.core.util.ApplicationContextUtils;
import com.icepoint.framework.web.system.entity.Module;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.entity.TableService;
import com.icepoint.framework.web.system.support.EntityUrlResolver;
import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public final class EntityUrlUtils {

    private EntityUrlUtils() {
    }

    @Nullable
    public static String getUrlByResourceId(Long id) {
        return getEntityUrlResolver().getUrlByResourceId(id);
    }

    @Nullable
    public static String getUrlByTableMetadataId(Long id) {
        return getEntityUrlResolver().getUrlByTableMetadataId(id);
    }

    @Nullable
    public static String getUrlByTableServiceId(Long id) {
        return getEntityUrlResolver().getUrlByTableServiceId(id);
    }

    @Nullable
    public static String getUrl(Class<?> entityType) {
        return getEntityUrlResolver().getUrl(entityType);
    }

    @Nullable
    public static Resource getResource(String url) {
        return getEntityUrlResolver().getResource(url);
    }

    @Nullable
    public static TableMetadata getTableMetadata(String url) {
        return getEntityUrlResolver().getTableMetadata(url);
    }

    @Nullable
    public static TableService getTableService(String url) {
        return getEntityUrlResolver().getTableService(url);
    }

    @Nullable
    public static Module getModule(String url) {
        return getEntityUrlResolver().getModule(url);
    }

    @Nullable
    public static <T> Class<T> getEntityType(String url) {
        return getEntityUrlResolver().getEntityType(url);
    }

    private static EntityUrlResolver getEntityUrlResolver() {
        return ApplicationContextUtils.getRequiredBean(EntityUrlResolver.class);
    }
}
