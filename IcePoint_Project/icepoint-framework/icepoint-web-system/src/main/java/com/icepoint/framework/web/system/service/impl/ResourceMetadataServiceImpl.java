package com.icepoint.framework.web.system.service.impl;

import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.resource.DefaultResourceModelMetadata;
import com.icepoint.framework.web.system.resource.ResourceModelMetadata;
import com.icepoint.framework.web.system.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ResourceMetadataServiceImpl implements ResourceMetadataService {

    private final AssetService assetService;

    private final ResourceService resourceService;

    private final FieldMetadataService fieldMetadataService;

    private final TableMetadataService tableMetadataService;

    @Override
    public ResourceModelMetadata findByAssetCode(String assetCode) {

        Assert.hasText(assetCode, "assetCode不能为空");

        Resource resource = assetService.getAssetDef(assetCode)
                .map(AssetDefine::getResourceId)
                .flatMap(resourceService::findById)
                .orElseThrow(() -> new IllegalArgumentException("资源查询失败"));

        return findByResource(resource);
    }

    @Override
    public ResourceModelMetadata findByResourceId(Long resourceId) {
        Assert.notNull(resourceId, "资源id不能为空");
        Resource resource = findRequiredResourceById(resourceId);
        return findByResource(resource);
    }

    @Override
    public ResourceModelMetadata findByTableMetadataId(Long tableId) {

        Assert.notNull(tableId, "表元数据id不能为空");

        TableMetadata table = findRequiredTableMetadataById(tableId);
        Resource resource = findRequiredResourceById(table.getResourceId());
        List<FieldMetadata> fields = findRequiredFieldMetadataByTableId(tableId);
        AssetDefine assetDefine = findRequiredAssetDefineByResourceId(resource.getId());

        return new DefaultResourceModelMetadata(resource, fields, table, assetDefine);
    }

    @Override
    public ResourceModelMetadata findByFieldMetadataId(Long fieldId) {

        Assert.notNull(fieldId, "字段元数据id不能为空");

        FieldMetadata field = fieldMetadataService.findById(fieldId)
                .orElseThrow(() -> new IllegalArgumentException("找不到字段元数据, id: " + fieldId));

        return findByTableMetadataId(field.getTableId());
    }

    private AssetDefine findRequiredAssetDefineByResourceId(Long resourceId) {
        return assetService.findByResourceId(resourceId)
                .orElseThrow(() -> new IllegalArgumentException("找不到资产定义, resource id: " + resourceId));
    }

    private Resource findRequiredResourceById(Long resourceId) {
        return resourceService.findById(resourceId)
                .orElseThrow(() -> new IllegalArgumentException("该资源不存在, id: " + resourceId));
    }

    private TableMetadata findRequiredTableMetadataById(Long tableId) {
        return tableMetadataService.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("找不到表元数据, id: " + tableId));
    }

    private List<FieldMetadata> findRequiredFieldMetadataByTableId(Long tableId) {
        List<FieldMetadata> fields = fieldMetadataService.findByTableId(tableId);
        Assert.notEmpty(fields, "字段元数据查询失败");
        return fields;
    }

    private DefaultResourceModelMetadata findByResource(Resource resource) {

        TableMetadata table = tableMetadataService.findByResourceId(resource.getId())
                .orElseThrow(() -> new IllegalArgumentException("表元数据查询失败"));

        List<FieldMetadata> fieldMetadataList = findRequiredFieldMetadataByTableId(table.getId());
        AssetDefine assetDefine = findRequiredAssetDefineByResourceId(resource.getId());

        return new DefaultResourceModelMetadata(resource, fieldMetadataList, table, assetDefine);
    }


}
