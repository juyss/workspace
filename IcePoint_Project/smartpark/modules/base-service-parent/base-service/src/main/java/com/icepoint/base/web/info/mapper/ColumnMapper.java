package com.icepoint.base.web.info.mapper;

import com.icepoint.base.web.basic.repository.MybatisMapper;
import com.icepoint.base.web.info.entity.Column;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 栏目映射
 *
 */
@Mapper
@Repository
public interface ColumnMapper extends MybatisMapper<Column, Long> {
    Integer maxSort(Column map);
}