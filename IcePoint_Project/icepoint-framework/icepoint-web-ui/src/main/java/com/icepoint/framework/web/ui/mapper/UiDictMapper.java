package com.icepoint.framework.web.ui.mapper;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.core.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UiDictMapper extends RepositoryMapper<Dict,Long> {
}
