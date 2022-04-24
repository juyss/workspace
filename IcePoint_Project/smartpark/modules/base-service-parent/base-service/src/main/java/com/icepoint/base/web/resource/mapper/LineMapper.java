package com.icepoint.base.web.resource.mapper;

import com.icepoint.base.web.basic.repository.MybatisMapper;
import com.icepoint.base.api.entity.Line;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LineMapper extends MybatisMapper<Line, Long> {

    int batchUpdate(List<Map<String, Object>> list);

    int batchAdd(List<Map<String, Object>> list);

    int batchDelete(List<Map<String, Object>> list);
}
