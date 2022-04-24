package com.icepoint.base.web.resource.service.complex;

import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.api.entity.Resource;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface ResourceMetadataService {

    @Nullable
    ResourceMetadata get(Long resId);

    @Nullable
    ResourceMetadata get(String key);

    @Nullable
    ResourceMetadata get(Resource resource);
}
