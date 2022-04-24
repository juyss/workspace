package com.github.tangyi.core.mybatis.mapper;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 专门用于通用Dao
 *
 * @author hedongzhou
 * @since 2018/11/26
 */
@RegisterMapper
public interface CommonDaoMapper<T> extends BaseMapper<T> {
}
