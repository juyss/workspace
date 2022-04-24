package com.icepoint.framework.web.system.resource.source;

import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceDataSourceResolveFailedException;

import java.util.Collection;

/**
 * @author Jiawei Zhao
 */
public interface ResourceDataSourceAdapter {

    ResourceDataSource getDataSource(Lookup lookup) throws ResourceDataSourceResolveFailedException;

    Collection<ResourceDataSource> getDataSources();

}
