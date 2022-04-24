package com.icepoint.framework.web.core.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.core.entity.Dict;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@MultiplyPathRepositoryRestResource(path = "/sys/dict")
@Repository
public interface DictRepository extends LongStdRepository<Dict> {

}
