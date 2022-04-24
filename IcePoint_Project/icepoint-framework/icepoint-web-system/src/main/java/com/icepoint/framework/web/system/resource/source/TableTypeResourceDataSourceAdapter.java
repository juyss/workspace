package com.icepoint.framework.web.system.resource.source;

import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.core.util.SimpleTypeUtils;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceDataSourceResolveFailedException;
import com.icepoint.framework.web.system.resource.ResourceModelMetadata;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jiawei Zhao
 */
public class TableTypeResourceDataSourceAdapter implements ResourceDataSourceAdapter {

    private final Map<String, ResourceDataSource> sourcesByIdentifier = new ConcurrentHashMap<>();

    public TableTypeResourceDataSourceAdapter() {
    }

    public TableTypeResourceDataSourceAdapter(Iterable<ResourceDataSource> dataSources) {
        dataSources.forEach(dataSource -> sourcesByIdentifier.put(dataSource.getIdentifier(), dataSource));
    }

    @Override
    public ResourceDataSource getDataSource(Lookup lookup) throws ResourceDataSourceResolveFailedException {
        String identifier = getIdentifier(lookup);
        ResourceDataSource dataSource = sourcesByIdentifier.get(identifier);
        if (dataSource == null) {
            throw new ResourceDataSourceResolveFailedException(identifier, sourcesByIdentifier.keySet().toArray(new String[0]));
        }

        return dataSource;
    }

    public void add(ResourceDataSource dataSource) {
        sourcesByIdentifier.merge(dataSource.getIdentifier(), dataSource, MapUtils.duplicateKeys());
    }

    @Override
    public Collection<ResourceDataSource> getDataSources() {
        return sourcesByIdentifier.values();
    }

    public String getIdentifier(Lookup lookup) {
        return Optional.of(lookup)
                .map(Lookup::getMetadata)
                .map(ResourceModelMetadata::getTable)
                .map(TableMetadata::getTabType)
                .map(SimpleTypeUtils::parseString)
                .orElseThrow(() -> new IllegalStateException("tab_type为空，无法获取资源数据源"));
    }
}
