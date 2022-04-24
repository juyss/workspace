package com.icepoint.framework.workflow.core.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.plugin.workflow.BpmNet;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author 
 */
@Repository
//@MultiplyPathRepositoryRestResource(path = "/bpm/net")
public interface BpmNetRepository extends LongStdRepository<BpmNet> {
}
