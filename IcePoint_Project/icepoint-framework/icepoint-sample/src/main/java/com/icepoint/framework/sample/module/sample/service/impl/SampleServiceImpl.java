package com.icepoint.framework.sample.module.sample.service.impl;

import com.icepoint.framework.sample.module.sample.dao.SampleMapper;
import com.icepoint.framework.sample.module.sample.dao.SampleRepository;
import com.icepoint.framework.sample.module.sample.entity.Sample;
import com.icepoint.framework.sample.module.sample.service.SampleService;
import com.icepoint.framework.web.core.support.BaseEntityServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jiawei Zhao
 */
@Service
@RequiredArgsConstructor
public class SampleServiceImpl extends BaseEntityServiceImpl<Sample, Long> implements SampleService {

    @Getter
    private final SampleRepository repository;

    @Getter
    private final SampleMapper mapper;

}
