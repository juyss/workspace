package com.icepoint.base.web.resource.repository;

import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.metadata.ResourceType;
import com.icepoint.base.web.resource.component.query.QueryParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@NoRepositoryBean
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface ResourceRepository<E, ID> extends Repository<E, ID> {

    ResourceType getResourceType();

    @Nullable
    E get(QueryParameter queryParameter, ID id, ResourceMetadata metadata, SerializeType serializeType);

    List<E> list(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType);

    Page<E> page(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType, Pageable pageable);

    @Transactional
    E add(Map<String, Object> entity, ResourceMetadata metadata);

    @Transactional
    int update(Map<String, Object> entity, ResourceMetadata metadata);

    @Transactional
    int delete(ID id, ResourceMetadata metadata);

    @Transactional
    int deleteAll(List<ID> id, ResourceMetadata metadata);

    @Transactional
    int approval(Map<String, Object> entity, ResourceMetadata metadata);
}
