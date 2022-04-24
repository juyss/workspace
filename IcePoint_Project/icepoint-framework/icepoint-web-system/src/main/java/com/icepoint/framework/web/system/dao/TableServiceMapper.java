package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.TableService;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SysTabService)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-04 16:12:28
 */
@Mapper
public interface TableServiceMapper extends RepositoryMapper<TableService, Long> {

}
