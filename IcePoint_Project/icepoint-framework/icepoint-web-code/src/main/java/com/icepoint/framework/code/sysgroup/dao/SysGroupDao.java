package com.icepoint.framework.code.sysgroup.dao;

import com.icepoint.framework.code.sysgroup.entity.SysGroup;
import com.icepoint.framework.data.mybatis.RepositoryMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分组表(SysGroup)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-05 11:04:09
 */
@Mapper
public interface SysGroupDao extends RepositoryMapper<SysGroup, Long> {

}
