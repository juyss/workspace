package com.icepoint.framework.web.core.response;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class MapResponse<K, V> extends DefaultResponse<Map<K, V>> {

    public MapResponse(@Nullable Map<K, V> data) {
        super(data);
    }

    @Override
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(getData());
    }
}
