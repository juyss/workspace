package com.icepoint.base.web.info.mapper;

import com.icepoint.base.web.basic.repository.MybatisMapper;
import com.icepoint.base.web.info.entity.Information;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 资讯映射
 *
 */
@Mapper
@Repository
public interface InformationsMapper extends MybatisMapper<Information, Long> {

}