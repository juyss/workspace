package com.icepoint.framework.web.system.service;

import com.icepoint.framework.data.annotation.WriteTransaction;
import com.icepoint.framework.web.core.support.BaseEntityService;
import com.icepoint.framework.web.system.entity.GenericDataSequence;
import com.icepoint.framework.web.system.resource.Lookup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
public interface GenericDataSequenceService extends BaseEntityService<GenericDataSequence, Long> {

    Page<String> findNoPage(Lookup lookup, Pageable pageable);

    @WriteTransaction
    Long nextId(Long resourceId);

    @WriteTransaction
    String nextNo(Long resourceId, Long assetDefineId);

    Optional<GenericDataSequence> findByResourceIdAndObjectId(Long resourceId, Long objectId);

    void deleteAllByResourceIdAndObjectId(Long resourceId, Long objectId);

    void updateNoByResourceIdAndObjectId(String no, Long resourceId, Long objectId);
}
