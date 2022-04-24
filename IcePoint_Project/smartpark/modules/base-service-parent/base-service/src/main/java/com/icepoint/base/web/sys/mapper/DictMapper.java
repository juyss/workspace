package com.icepoint.base.web.sys.mapper;

import com.icepoint.base.web.basic.repository.MybatisMapper;
import com.icepoint.base.web.sys.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DictMapper extends MybatisMapper<Dict, Long> {
}
