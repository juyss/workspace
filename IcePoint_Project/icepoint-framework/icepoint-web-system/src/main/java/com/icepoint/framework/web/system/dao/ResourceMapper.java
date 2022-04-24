package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源表(Resource)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-27 10:41:25
 */
@Mapper
public interface ResourceMapper extends RepositoryMapper<Resource, Long> {

}
