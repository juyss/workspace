package com.icepoint.framework.web.core.support;

/**
 * @author Jiawei Zhao
 */
public interface EntityMetadataProvider<T,ID> {

    EntityMetadata<T, ID> getEntityMetadata();
}
