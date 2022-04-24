package com.icepoint.base.web.resource.repository;

import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.query.QueryParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface ResourceRepositoryImplementor<E, ID> extends ResourceRepository<E, ID> {

    @Override
    default E get(QueryParameter queryParameter, ID objectId, ResourceMetadata metadata, SerializeType serializeType) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    default List<E> list(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    default Page<E> page(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    default E add(Map<String, Object> entity, ResourceMetadata metadata) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    default int update(Map<String, Object> entity, ResourceMetadata metadata) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    default int delete(ID id, ResourceMetadata metadata) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    default int deleteAll(List<ID> id, ResourceMetadata metadata) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    default int approval(Map<String, Object> entity, ResourceMetadata metadata) {
        throw new UnsupportedOperationException("Not implemented");
    }

    default void download(HttpServletRequest request, HttpServletResponse response, String key, List<String> docNoList) {
        throw new UnsupportedOperationException("Not implemented");
    }

    default List<Object> excel(ResourceMetadata metadata, String key, MultipartFile excel) {
        throw new UnsupportedOperationException("Not implemented");
    }

    default List<GenericEntity> geom(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType, Integer geomType){
        throw new UnsupportedOperationException("Not implemented");
    }
}
