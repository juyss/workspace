package com.icepoint.base.web.resource.repository;

import com.github.tangyi.common.basic.vo.UserVo;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.util.ServiceOperation;
import com.icepoint.base.api.util.StreamUtils;
import com.icepoint.base.util.UserUtils;
import com.icepoint.base.web.basic.service.ServiceTemplate;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.query.*;
import com.icepoint.base.web.resource.util.GenericUtils;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link GenericRepository}的模版方法，主要在读写数据前后做一些处理，
 * 继承这个类，让实现类的代码集中在读写数据的逻辑上，提高代码的可维护性
 *
 * @author Jiawei Zhao
 */
public abstract class GenericRepositoryTemplate
        implements GenericRepository, GenericEntityServiceSupports, ServiceTemplate {

    // Get Methods ------------------------------------------------------------------------------------------

    @Override
    public GenericEntity get(
            QueryParameter queryParameter, Object objectId,
            ResourceMetadata metadata, SerializeType serializeType) {

        beforeGet(queryParameter, objectId, metadata, serializeType);
        GenericEntity entity = doGet(queryParameter, objectId, metadata, serializeType);

        if (GenericUtils.isEmpty(entity))
            return null;

        // 关联表
        queryAndSetAssociationEntities(metadata, serializeType, entity, queryParameter);

        // 添加缺失的列表字段为空串
        addMissingFieldsToEmptyString(metadata, entity, ServiceOperation.GET);

        dictionaryization(entity, metadata, serializeType);
        return afterGet(entity, objectId, metadata, serializeType);
    }

    @Nullable
    protected GenericEntity afterGet(
            @Nullable GenericEntity entity, Object objectId,
            ResourceMetadata metadata, SerializeType serializeType) {
        return entity;
    }

    @Nullable
    protected abstract GenericEntity doGet(
            QueryParameter queryParameter, Object objectId,
            ResourceMetadata metadata, SerializeType serializeType);

    protected void beforeGet(
            QueryParameter queryParameter, Object objectId,
            ResourceMetadata metadata, SerializeType serializeType) {
    }

    // List Methods ------------------------------------------------------------------------------------------

    @Override
    public List<GenericEntity> list(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType) {
        beforeList(queryParameter, metadata, serializeType);
        List<GenericEntity> entities = doList(queryParameter, metadata, serializeType);
        if (CollectionUtils.isEmpty(entities))
            return Collections.emptyList();

        entities.forEach(entity -> addMissingFieldsToEmptyString(metadata, entity, ServiceOperation.LIST));

        dictionaryization(entities, metadata, serializeType);

        return afterList(entities, metadata, serializeType);
    }

    protected void beforeList(
            QueryParameter queryParameter, ResourceMetadata metadata,
            SerializeType serializeType) {
    }

    protected abstract List<GenericEntity> doList(
            QueryParameter queryParameter, ResourceMetadata metadata,
            SerializeType serializeType);

    protected List<GenericEntity> afterList(
            List<GenericEntity> entities, ResourceMetadata metadata,
            SerializeType serializeType) {
        return entities;
    }

    // Page Methods ------------------------------------------------------------------------------------------

    @Override
    public Page<GenericEntity> page(
            QueryParameter queryParameter, ResourceMetadata metadata,
            SerializeType serializeType, Pageable pageable) {

        beforePage(queryParameter, metadata, serializeType, pageable);
//        planQueryParameter(queryParameter, metadata); //规划管理特殊处理
        Page<GenericEntity> entities = doPage(queryParameter, metadata, serializeType, pageable);
        if (CollectionUtils.isEmpty(entities.getContent()))
            return Page.empty();

        entities.forEach(entity -> addMissingFieldsToEmptyString(metadata, entity, ServiceOperation.PAGE));
        return afterPage(entities, metadata, serializeType, pageable);
    }

    protected void beforePage(
            QueryParameter queryParameter, ResourceMetadata metadata,
            SerializeType serializeType, Pageable pageable) {

    }

    protected abstract Page<GenericEntity> doPage(
            QueryParameter queryParameter, ResourceMetadata metadata,
            SerializeType serializeType, Pageable pageable);

    protected Page<GenericEntity> afterPage(
            Page<GenericEntity> entities, ResourceMetadata metadata,
            SerializeType serializeType, Pageable pageable) {
        return entities;
    }

    // Add Methods ------------------------------------------------------------------------------------------

    @Override
    public GenericEntity add(Map<String, Object> entity, ResourceMetadata metadata) {
        beforeAdd(entity, metadata);
        setCommonValuesIfAbsent(entity, metadata, ServiceOperation.ADD);
        assertLegalParameter(entity, metadata, ServiceOperation.ADD);
        return doAdd(entity, metadata);
    }

    protected void beforeAdd(Map<String, Object> entity, ResourceMetadata metadata) {
    }

    protected abstract GenericEntity doAdd(Map<String, Object> entity, ResourceMetadata metadata);

    // Update Methods ------------------------------------------------------------------------------------------

    @Override
    public int update(Map<String, Object> entity, ResourceMetadata metadata) {
        beforeUpdate(entity, metadata);
        setCommonValuesIfAbsent(entity, metadata, ServiceOperation.UPDATE);
        assertLegalParameter(entity, metadata, ServiceOperation.UPDATE);
        return doUpdate(entity, metadata);
    }

    protected void beforeUpdate(Map<String, Object> entity, ResourceMetadata metadata) {
    }

    protected abstract int doUpdate(Map<String, Object> entity, ResourceMetadata metadata);

    // Delete Methods ------------------------------------------------------------------------------------------

    @Override
    public int delete(Object id, ResourceMetadata metadata) {
        beforeDelete(id, metadata);
        List<MetaField> primaryKeyField = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                .filter(metaField -> 1 == metaField.getPrimaryKey())
                .collect(Collectors.toList());
        Assert.isTrue(1 == primaryKeyField.size(), "缺少主键值");
        return doDelete(id, metadata);
    }

    protected void beforeDelete(Object id, ResourceMetadata metadata) {
    }

    protected abstract int doDelete(Object id, ResourceMetadata metadata);

    // Delete All Methods ------------------------------------------------------------------------------------------

    @Override
    public int deleteAll(List<Object> id, ResourceMetadata metadata) {
        beforeDeleteAll(id, metadata); //条件主键
        List<MetaField> primaryKeyField = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                .filter(metaField -> 1 == metaField.getPrimaryKey())
                .collect(Collectors.toList());
        Assert.isTrue(1 == primaryKeyField.size(), "缺少主键值");
        return doDeleteAll(id, metadata);
    }

    protected void beforeDeleteAll(List<Object> id, ResourceMetadata metadata) {
    }

    protected abstract int doDeleteAll(List<Object> id, ResourceMetadata metadata);

    @Override
    public List<GenericEntity> geom(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType, Integer geomType) {
        beforeList(queryParameter, metadata, serializeType);
        List<GenericEntity> entities = doGeom(queryParameter, metadata, serializeType);
        if (CollectionUtils.isEmpty(entities))
            return Collections.emptyList();

        entities.forEach(entity -> addMissingFieldsToEmptyString(metadata, entity, ServiceOperation.LIST));

        dictionaryization(entities, metadata, serializeType);

        return afterList(entities, metadata, serializeType);
    }

    protected abstract List<GenericEntity> doGeom(
            QueryParameter queryParameter, ResourceMetadata metadata,
            SerializeType serializeType);
}
