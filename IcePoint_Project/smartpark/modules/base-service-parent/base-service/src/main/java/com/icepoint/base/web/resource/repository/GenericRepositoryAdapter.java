package com.icepoint.base.web.resource.repository;

import com.icepoint.base.web.resource.component.metadata.ResourceType;

/**
 * 资源数据仓储适配器，用来根据不同情况进行适配，获取合适的ResourceRepository
 *
 * @author Jiawei Zhao
 * @see ResourceType
 * @see GenericRepository
 */
public interface GenericRepositoryAdapter {

    /**
     * 根据提供的{@link ResourceType}进行适配，获取{@link GenericRepository ResourceRepository}
     *
     * @param resourceType 资源类型
     * @return {@link GenericRepository}
     */
    GenericRepository get(ResourceType resourceType);

    GenericRepository get(String beanName);
}
