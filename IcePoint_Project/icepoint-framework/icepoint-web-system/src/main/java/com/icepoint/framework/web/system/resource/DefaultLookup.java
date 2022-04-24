package com.icepoint.framework.web.system.resource;

import com.icepoint.framework.web.system.resource.query.Query;
import lombok.Data;

/**
 * @author Jiawei Zhao
 */
@Data
public class DefaultLookup implements Lookup {

    private final ResourceModelMetadata metadata;

    private Query query;

    private final ViewType viewType;

    public DefaultLookup(ResourceModelMetadata metadata, ViewType viewType, Query query) {
        this.metadata = metadata;
        this.query = query;
        this.viewType = viewType;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
