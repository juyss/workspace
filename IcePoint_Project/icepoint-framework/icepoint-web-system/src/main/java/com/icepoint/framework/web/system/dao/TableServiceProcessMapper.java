package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.TableServiceProcess;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SysTableServiceProcess)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-17 09:47:30
 */
@Mapper
public interface TableServiceProcessMapper extends RepositoryMapper<TableServiceProcess, Long> {
}

