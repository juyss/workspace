package com.github.tangyi.core.mybatis.mapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * BaseMapper
 *
 * @author hedongzhou
 * @since 2018/11/26
 */
@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
