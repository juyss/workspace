package com.icepoint.framework.web.core.response;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @author Jiawei Zhao
 */
public class CollectionResponse<T> extends DefaultResponse<Collection<T>> {

    public CollectionResponse(@Nullable Collection<T> collection) {
        super(collection);
    }

    @Override
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(getData());
    }
}
