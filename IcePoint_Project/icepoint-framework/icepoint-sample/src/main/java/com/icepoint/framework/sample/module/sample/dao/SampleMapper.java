package com.icepoint.framework.sample.module.sample.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.sample.module.sample.entity.Sample;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jiawei Zhao
 */
@Mapper
public interface SampleMapper extends RepositoryMapper<Sample, Long> {
}
