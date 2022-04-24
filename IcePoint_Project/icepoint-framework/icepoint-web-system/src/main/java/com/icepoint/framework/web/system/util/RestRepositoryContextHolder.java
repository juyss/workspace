package com.icepoint.framework.web.system.util;

import com.icepoint.framework.core.util.ApplicationContextUtils;
import org.springframework.data.rest.core.mapping.ResourceMappings;

/**
 * @author Jiawei Zhao
 */
public class RestRepositoryContextHolder {

    private static ResourceMappings mappings;

    private RestRepositoryContextHolder() {
        throw new UnsupportedOperationException();
    }

    public static ResourceMappings getMappings() {

        if (mappings == null) {
            mappings = ApplicationContextUtils.getContext().getBean(ResourceMappings.class);
        }

        return mappings;
    }
}
