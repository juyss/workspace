package com.icepoint.base.api.domain;

import com.icepoint.base.api.entity.MetaTab;
import org.springframework.util.Assert;

public abstract class AbstractGenericEntity implements GenericEntity{

    /**
     * 资源的key值，与配置文件generic-resource.yml中的generic-table.entities.key值对应
     */
    private final String key;

    private final MetaTab metadata;

    public AbstractGenericEntity(String key, MetaTab metadata) {
        Assert.hasText(key, "Key must not be empty.");
        Assert.notNull(metadata, "Metadata must not be null.");

        this.key = key;
        this.metadata = metadata;
    }

    @Override
    public MetaTab getMetadata() {
        return metadata;
    }

    @Override
    public String getName() {
        return metadata.getName();
    }

    @Override
    public String getKey() {
        return key;
    }
}
