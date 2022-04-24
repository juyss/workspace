package com.icepoint.framework.web.system.resource;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public interface ResourceModelConverter {

    ResourceModel convert(Lookup lookup, Map<String, Object> properties);
}
