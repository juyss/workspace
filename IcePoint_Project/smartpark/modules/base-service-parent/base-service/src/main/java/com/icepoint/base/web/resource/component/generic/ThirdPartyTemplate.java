package com.icepoint.base.web.resource.component.generic;

import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.web.resource.component.metadata.ResourceType;
import com.icepoint.base.web.resource.repository.ResourceRepositoryImplementor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ThirdPartyTemplate implements ResourceRepositoryImplementor<GenericEntity, Object> {

    private final String hostAndPort;

    @Override
    public ResourceType getResourceType() {
        return ResourceType.THIRD_PARTY;
    }


}
