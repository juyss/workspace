package com.icepoint.framework.web.system.resource.source;

import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractResourceDataSource extends ResourceDataSourceSupport implements ResourceDataSource {

    private static final String NOT_IMPLEMENTED = "该资源数据源不支持当前操作, 当前操作: %s, 资源: %s";

    private static final String FIND_ALL = "findAll";

    @Override
    public Optional<ResourceModel> findById(Lookup lookup, Object id) {
        Resource resource = lookup.getMetadata().getResource();
        throw new UnsupportedOperationException(String.format(NOT_IMPLEMENTED, "findById", resource));
    }

    @Override
    public Optional<ResourceModel> findOne(Lookup lookup) {
        Resource resource = lookup.getMetadata().getResource();
        throw new UnsupportedOperationException(String.format(NOT_IMPLEMENTED, "findOne", resource));
    }

    @Override
    public List<ResourceModel> findAll(Lookup lookup) {
        Resource resource = lookup.getMetadata().getResource();
        throw new UnsupportedOperationException(String.format(NOT_IMPLEMENTED, FIND_ALL, resource));
    }

    @Override
    public List<ResourceModel> findAll(Lookup lookup, Sort sort) {
        Resource resource = lookup.getMetadata().getResource();
        throw new UnsupportedOperationException(String.format(NOT_IMPLEMENTED, FIND_ALL, resource));
    }

    @Override
    public Page<ResourceModel> findAll(Lookup lookup, Pageable pageable) {
        Resource resource = lookup.getMetadata().getResource();
        throw new UnsupportedOperationException(String.format(NOT_IMPLEMENTED, FIND_ALL, resource));
    }

    @Override
    public ResourceModel save(ResourceModel model) {
        Resource resource = model.getLookup().getMetadata().getResource();
        throw new UnsupportedOperationException(String.format(NOT_IMPLEMENTED, "save", resource));
    }

    @Override
    public List<ResourceModel> saveAll(Iterable<ResourceModel> models) {
        return Streams.streamable(models)
                .map(this::save)
                .toList();
    }

    @Override
    public void deleteById(Lookup lookup, Object id) {
        Resource resource = lookup.getMetadata().getResource();
        throw new UnsupportedOperationException(String.format(NOT_IMPLEMENTED, "deleteById", resource));
    }

    @Override
    public void deleteAllByIds(Lookup lookup, Iterable<Object> ids) {
        Resource resource = lookup.getMetadata().getResource();
        throw new UnsupportedOperationException(String.format(NOT_IMPLEMENTED, "deleteAll", resource));
    }
}
