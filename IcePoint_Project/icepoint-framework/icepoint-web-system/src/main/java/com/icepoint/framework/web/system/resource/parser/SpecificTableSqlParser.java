package com.icepoint.framework.web.system.resource.parser;

import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModel;

/**
 * @author Jiawei Zhao
 */
public interface SpecificTableSqlParser {

    String findById(Lookup lookup, Object id);

    String findOne(Lookup lookup);

    String findAll(Lookup lookup);

    String add(ResourceModel model);

    String addAll(Iterable<ResourceModel> models);

    String update(ResourceModel model);

    String deleteById(Lookup lookup, Object id);

    String deleteAllByIds(Lookup lookup, Iterable<Object> ids);
}
