package com.icepoint.framework.web.system.service;

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
public interface ResourceModelService {

    Optional<ResourceModel> findById(Lookup lookup, Object id);

    Optional<ResourceModel> findOne(Lookup lookup);

    List<ResourceModel> findAll(Lookup lookup);

    List<ResourceModel> findAll(Lookup lookup, Sort sort);

    Page<ResourceModel> findAll(Lookup lookup, Pageable pageable);

    ResourceModel save(ResourceModel model);

    List<ResourceModel> saveAll(Iterable<ResourceModel> models);

    void deleteById(Lookup lookup, Object id);

    void deleteAllByIds(Lookup lookup, Iterable<Object> ids);

}
