package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.Label;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface LabelMapper extends RepositoryMapper<Label,Long> {

}
