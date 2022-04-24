package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.dao.LongBaseRepository;
import com.icepoint.framework.web.system.entity.GenericDataSequence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@Repository
public interface GenericDataSequenceRepository extends LongBaseRepository<GenericDataSequence> {

    @Query(value = "SELECT f_generic_sequence_next_id(:resourceId)", nativeQuery = true)
    Long nextId(@Param("resourceId") Long resourceId);

    @Query(value = "SELECT f_generic_sequence_next_no(:resourceId, :assetDefineId)", nativeQuery = true)
    String nextNo(@Param("resourceId") Long resourceId, @Param("assetDefineId") Long assetDefineId);
}
