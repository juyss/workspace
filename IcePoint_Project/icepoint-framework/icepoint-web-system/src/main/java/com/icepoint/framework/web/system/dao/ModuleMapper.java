package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.Module;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-11 16:29
 */
@Mapper
public interface ModuleMapper extends RepositoryMapper<Module, Long> {
}
