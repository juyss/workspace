package com.icepoint.framework.web.ui.mapper;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.ui.entity.UiLessFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UiLessFileMapper extends RepositoryMapper<UiLessFile,Long> {
}
