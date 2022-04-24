package com.icepoint.framework.web.core.support;

import com.icepoint.framework.data.domain.BaseEntity;

import java.io.Serializable;

/**
 * @author Jiawei Zhao
 */
public interface EntityServiceProcessor<T extends BaseEntity<ID>, ID extends Serializable> {

    T afterSingleResultQuery(T result);

    Iterable<T> afterManyResultQuery(Iterable<T> result);

    T beforeSave(T entity);

    T beforeSave(T entity, SaveType type);

    T afterSave(T result);

    T afterSave(T result, SaveType type);

    void beforeDelete(ID id);

    void afterDelete(ID id);
}
