package com.icepoint.framework.workflow.core.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.workflow.core.entity.BpmTermData;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author 
 */
@Repository
//@MultiplyPathRepositoryRestResource(path = "/bpm/termData")
public interface BpmTermDataRepository extends LongStdRepository<BpmTermData> {
}
