package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.Parameter;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Parameter)表数据库访问层
 *
 * @author ck
 * @since 2021-05-25 16:48:03
 */
@Mapper
public interface ParameterMapper extends RepositoryMapper<Parameter, Long> {

}
