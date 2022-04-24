package com.icepoint.framework.web.system.service;

import com.icepoint.framework.web.core.support.StdEntityService;
import com.icepoint.framework.web.system.entity.GenericData;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public interface GenericDataService extends StdEntityService<GenericData, Long> {

    List<GenericData> findAllByResourceIdAndObjectId(Long resourceId, Long objectId);

    List<GenericData> findAllByResourceIdAndNos(Long resourceId, List<String> nos);

    GenericData updateByResourceIdAndNoAndName(GenericData genericData);

    void deleteAllByResourceIdAndObjectId(Long resourceId, Long objectId);
}
