package com.icepoint.framework.web.system.service;

import com.icepoint.framework.web.system.resource.ResourceModelMetadata;

/**
 * 用于获取{@link ResourceModelMetadata}的服务
 *
 * @author Jiawei Zhao
 */
public interface ResourceMetadataService {

    /**
     * 根据资产编码查询{@link ResourceModelMetadata}，查询失败时会抛出异常
     *
     * @param assetCode 资产编码
     * @return {@link ResourceModelMetadata}
     */
    ResourceModelMetadata findByAssetCode(String assetCode);

    /**
     * 根据资源id查询{@link ResourceModelMetadata}，查询失败时会抛出异常
     *
     * @param resourceId 资源id
     * @return {@link ResourceModelMetadata}
     */
    ResourceModelMetadata findByResourceId(Long resourceId);

    /**
     * 根据表元数据id查询{@link ResourceModelMetadata}，查询失败时会抛出异常
     *
     * @param tableId 表元数据id
     * @return {@link ResourceModelMetadata}
     */
    ResourceModelMetadata findByTableMetadataId(Long tableId);

    /**
     * 根据字段元数据id查询{@link ResourceModelMetadata}，查询失败时会抛出异常
     *
     * @param fieldId 字段元数据id
     * @return {@link ResourceModelMetadata}
     */
    ResourceModelMetadata findByFieldMetadataId(Long fieldId);

}
