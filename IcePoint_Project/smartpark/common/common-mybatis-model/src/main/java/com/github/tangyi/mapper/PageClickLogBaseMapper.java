package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.PageClickLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * page_click_log 页点点击日志表
 *
 * @author xh
 * @since 2020/11/13
 */
@Mapper
public interface PageClickLogBaseMapper extends CommonDaoMapper<PageClickLog> {
}
