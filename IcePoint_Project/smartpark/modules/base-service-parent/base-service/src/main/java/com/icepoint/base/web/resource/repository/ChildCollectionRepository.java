package com.icepoint.base.web.resource.repository;

import com.icepoint.base.web.resource.component.metadata.ResourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 子集合资源数据仓储
 * TODO: jiawei: 逻辑未知
 *
 * @author Jiawei Zhao
 */
@Component
@RequiredArgsConstructor
public class ChildCollectionRepository implements GenericRepository {

    @Override
    public ResourceType getResourceType() {
        return ResourceType.CHILD_COLLECTION;
    }

}
