package com.icepoint.framework.code.sysfunservice.dao;

import com.icepoint.framework.code.sysfunservice.entity.SysFunService;
import com.icepoint.framework.data.mybatis.RepositoryMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SysFunService)表数据库访问层
 *
 * @author ck
 * @since 2021-06-04 17:44:23
 */
@Mapper
public interface SysFunServiceDao extends RepositoryMapper<SysFunService,Long> {
    
}
