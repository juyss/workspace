package com.icepoint.framework.sample.module.sample.service;

import com.icepoint.framework.sample.module.sample.dao.SampleMapper;
import com.icepoint.framework.sample.module.sample.dao.SampleRepository;
import com.icepoint.framework.sample.module.sample.entity.Sample;
import com.icepoint.framework.web.core.support.BaseEntityService;

/**
 * @author Jiawei Zhao
 */
public interface SampleService extends BaseEntityService<Sample, Long> {

    SampleRepository getRepository();

    SampleMapper getMapper();
}
