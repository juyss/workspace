package com.icepoint.framework.web.system.support;

import com.icepoint.framework.web.system.entity.Module;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.entity.TableService;
import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public interface EntityUrlResolver {

    @Nullable
    String getUrlByResourceId(Long id);

    @Nullable
    String getUrlByTableMetadataId(Long id);

    @Nullable
    String getUrlByTableServiceId(Long id);

    @Nullable
    String getUrl(Class<?> entityType);

    @Nullable
    Resource getResource(String url);

    @Nullable
    TableMetadata getTableMetadata(String url);

    @Nullable
    TableService getTableService(String url);

    @Nullable
    Module getModule(String url);

    @Nullable
    <T> Class<T> getEntityType(String url);

}
