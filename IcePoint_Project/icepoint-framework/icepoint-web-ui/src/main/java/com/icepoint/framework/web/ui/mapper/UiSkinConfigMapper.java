package com.icepoint.framework.web.ui.mapper;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.ui.entity.UiSkinConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * (UiSkinConfig)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-18 16:31:51
 */
@Mapper
public interface UiSkinConfigMapper extends RepositoryMapper<UiSkinConfig,Long> {


}

