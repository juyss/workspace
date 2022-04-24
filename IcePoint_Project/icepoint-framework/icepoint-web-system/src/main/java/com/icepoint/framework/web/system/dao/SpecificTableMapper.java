package com.icepoint.framework.web.system.dao;


import com.icepoint.framework.data.annotation.WriteTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
@Mapper
public interface SpecificTableMapper {

    Page<Map<String, Object>> findAll(@Param("sql") String sql, Pageable pageable);

    @WriteTransaction
    int add(@Param("sql") String sql);

    @WriteTransaction
    int update(@Param("sql") String sql);

}
