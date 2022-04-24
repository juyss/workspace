package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.system.entity.TableMetadata;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@Repository
public interface TableMetadataRepository extends LongStdRepository<TableMetadata> {
}
