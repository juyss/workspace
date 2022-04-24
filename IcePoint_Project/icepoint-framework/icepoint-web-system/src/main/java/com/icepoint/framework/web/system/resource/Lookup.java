package com.icepoint.framework.web.system.resource;

import com.icepoint.framework.web.system.resource.query.Query;

/**
 * @author Jiawei Zhao
 */
public interface Lookup {

    ResourceModelMetadata getMetadata();

    Query getQuery();

    ViewType getViewType();
}
