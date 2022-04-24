package com.icepoint.framework.web.ui.mapper;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.ui.entity.UiMenuTemplate;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface UiMenuTemplateMapper extends RepositoryMapper<UiMenuTemplate,Long> {
}
