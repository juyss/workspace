package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.Project;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SysProject)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-24 16:57:36
 */
@Mapper
public interface ProjectMapper extends RepositoryMapper<Project,Long> {

}
