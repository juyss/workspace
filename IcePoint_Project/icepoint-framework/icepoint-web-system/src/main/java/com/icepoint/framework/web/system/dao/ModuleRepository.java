package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import com.icepoint.framework.web.system.entity.Module;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@MultiplyPathRepositoryRestResource(path = "/sys/module")
@Repository
public interface ModuleRepository extends LongStdRepository<Module> {
}
