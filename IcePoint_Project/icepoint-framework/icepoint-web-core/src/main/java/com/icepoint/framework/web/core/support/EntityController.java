package com.icepoint.framework.web.core.support;

import com.icepoint.framework.data.domain.BaseEntity;

import java.io.Serializable;

/**
 * 通用实体Controller父类
 *
 * @author Jiawei Zhao
 */
public abstract class EntityController<
        S extends BaseEntityService<T, ID>,
        T extends BaseEntity<ID>,
        ID extends Serializable>
        extends AbstractController<S>
        implements EntityMetadataProvider<T, ID> {

    @Override
    public EntityMetadata<T, ID> getEntityMetadata() {
        return service.getEntityMetadata();
    }

}
