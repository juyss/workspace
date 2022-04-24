package com.icepoint.base.web.entp.mapper;

import com.icepoint.base.api.entity.QueryCondition;
import com.icepoint.base.web.basic.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QueryConditionMapper extends MybatisMapper<QueryCondition, Long> {

    void add(QueryCondition queryCondition);

    List<String> listName();

    List<QueryCondition> getListByName(String name);

    Integer deleted(Long id);
}
