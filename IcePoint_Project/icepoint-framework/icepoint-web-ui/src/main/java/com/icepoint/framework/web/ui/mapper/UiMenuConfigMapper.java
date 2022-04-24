package com.icepoint.framework.web.ui.mapper;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.ui.entity.UiMenuConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface UiMenuConfigMapper extends RepositoryMapper<UiMenuConfig,Long> {
}
