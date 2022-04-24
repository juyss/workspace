package com.icepoint.framework.code.sysfunction.dao;

import com.icepoint.framework.code.sysfunction.entity.SysFunction;
import com.icepoint.framework.data.mybatis.RepositoryMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SysFunction)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-07 10:05:57
 */
@Mapper
public interface SysFunctionDao extends RepositoryMapper<SysFunction, Long> {

}
