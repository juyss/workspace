package com.icepoint.framework.sample.module.sample.dao;

import com.icepoint.framework.data.dao.BaseRepository;
import com.icepoint.framework.sample.module.sample.entity.Sample;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@MultiplyPathRepositoryRestResource(path = "/sample")
@Repository
public interface SampleRepository extends BaseRepository<Sample, Long> {
}
