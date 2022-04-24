package com.icepoint.framework.web.system.service.impl;

import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceAccessProcessor;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.query.ConditionAddingVisitor;
import com.icepoint.framework.web.system.resource.query.LogicCondition;
import com.icepoint.framework.web.system.resource.query.Operation;
import com.icepoint.framework.web.system.resource.query.Parameter;
import com.icepoint.framework.web.system.resource.source.ResourceDataSourceAdapter;
import com.icepoint.framework.web.system.service.ResourceModelService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.icepoint.framework.web.system.util.SystemResourceUtils.*;

/**
 * @author Jiawei Zhao
 */
@Service
public class ResourceModelServiceImpl implements ResourceModelService {

    private final ResourceDataSourceAdapter adapter;

    private final List<ResourceAccessProcessor> processors;

    public ResourceModelServiceImpl(
            ResourceDataSourceAdapter adapter,
            ObjectProvider<ResourceAccessProcessor> processors) {

        this.adapter = adapter;
        this.processors = processors.orderedStream().collect(Collectors.toList());
    }

    @Override
    public Optional<ResourceModel> findById(Lookup lookup, Object id) {
        processors.forEach(processor -> processor.preLoad(lookup));
        return adapter.getDataSource(lookup).findById(lookup, id);
    }

    @Override
    public Optional<ResourceModel> findOne(Lookup lookup) {
        processors.forEach(processor -> processor.preLoad(lookup));
        return adapter.getDataSource(lookup).findOne(lookup);
    }

    @Override
    public List<ResourceModel> findAll(Lookup lookup) {
        processors.forEach(processor -> processor.preLoad(lookup));
        return adapter.getDataSource(lookup).findAll(lookup);
    }

    @Override
    public List<ResourceModel> findAll(Lookup lookup, Sort sort) {
        processors.forEach(processor -> processor.preLoad(lookup));
        return adapter.getDataSource(lookup).findAll(lookup, sort);
    }

    @Override
    public Page<ResourceModel> findAll(Lookup lookup, Pageable pageable) {
        processors.forEach(processor -> processor.preLoad(lookup));
        return adapter.getDataSource(lookup).findAll(lookup, pageable);
    }

    @Override
    public ResourceModel save(ResourceModel model) {
        processors.forEach(processor -> processor.prePersist(model));
        return adapter.getDataSource(model.getLookup()).save(model);
    }

    @Override
    public List<ResourceModel> saveAll(Iterable<ResourceModel> models) {
        processors.forEach(processor -> models.forEach(processor::prePersist));
        return Streams.stream(models)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Lookup lookup, Object id) {
        processors.forEach(processor -> processor.preDeleteById(lookup, id));
        FieldMetadata deleteMarker = getUniqueDeleteMarker(lookup.getMetadata().getFields())
                .orElse(null);
        if (deleteMarker != null) {
            ResourceModel model = findById(lookup, id)
                    .orElseThrow(() -> new ResourceNotFoundException("要删除的数据不存在, id: " + id));
            model.setProperty(getFieldName(deleteMarker), 1);
            save(model);
        } else {
            adapter.getDataSource(lookup).deleteById(lookup, id);
        }

    }

    @Override
    public void deleteAllByIds(Lookup lookup, Iterable<Object> ids) {
        processors.forEach(processor -> ids.forEach(id -> processor.preDeleteById(lookup, id)));
        List<FieldMetadata> fields = lookup.getMetadata().getFields();
        FieldMetadata deleteMarker = getUniqueDeleteMarker(fields)
                .orElse(null);

        if (deleteMarker != null) {
            FieldMetadata primaryKey = getRequiredUniquePrimaryKey(fields);
            ConditionAddingVisitor visitor = new ConditionAddingVisitor();
            visitor.setOverwrite(true);
            visitor.addParameter(new Parameter(
                    Long.class,
                    getFieldName(primaryKey),
                    Streams.stream(ids).toArray(),
                    Operation.IN,
                    LogicCondition.AND));

            lookup.getQuery().accept(visitor);
            List<ResourceModel> models = findAll(lookup);
            models.forEach(model -> model.setProperty(getFieldName(deleteMarker), 1));
            saveAll(models);
        } else {
            adapter.getDataSource(lookup).deleteAllByIds(lookup, ids);
        }
    }
}
