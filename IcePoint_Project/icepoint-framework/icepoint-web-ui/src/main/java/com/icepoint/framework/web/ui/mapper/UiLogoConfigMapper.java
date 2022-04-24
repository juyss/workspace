package com.icepoint.framework.web.ui.mapper;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.ui.entity.UiLogoConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UiLogoConfigMapper extends RepositoryMapper<UiLogoConfig,Long> {
}
