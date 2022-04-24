package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import com.icepoint.framework.web.system.entity.Resource;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@MultiplyPathRepositoryRestResource(path = "/sys/resource")
@Repository
public interface ResourceRepository extends LongStdRepository<Resource> {
}
