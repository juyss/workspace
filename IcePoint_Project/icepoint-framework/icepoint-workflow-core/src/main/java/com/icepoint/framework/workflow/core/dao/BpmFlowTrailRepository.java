package com.icepoint.framework.workflow.core.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.workflow.core.entity.BpmFlowTrail;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author 
 */
@Repository
//@MultiplyPathRepositoryRestResource(path = "/bpm/trail")
public interface BpmFlowTrailRepository extends LongStdRepository<BpmFlowTrail> {
}
