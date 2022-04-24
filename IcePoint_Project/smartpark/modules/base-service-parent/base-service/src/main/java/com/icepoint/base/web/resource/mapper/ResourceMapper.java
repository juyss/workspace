package com.icepoint.base.web.resource.mapper;

import com.icepoint.base.web.basic.repository.MybatisMapper;
import com.icepoint.base.api.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ResourceMapper extends MybatisMapper<Resource, Long> {
}
