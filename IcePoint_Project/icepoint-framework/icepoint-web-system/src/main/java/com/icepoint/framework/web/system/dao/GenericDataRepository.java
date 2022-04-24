package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.system.entity.GenericData;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@Repository
public interface GenericDataRepository extends LongStdRepository<GenericData> {

    Optional<GenericData> findByResourceIdAndNoAndName(Long resourceId, String no, String name);
}
