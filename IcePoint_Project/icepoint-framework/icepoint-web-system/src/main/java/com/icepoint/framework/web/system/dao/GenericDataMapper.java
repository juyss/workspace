package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.GenericData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jiawei Zhao
 */
@Mapper
public interface GenericDataMapper extends RepositoryMapper<GenericData, Long> {

    void deleteAllByResourceIdAndNo(@Param("resourceId") Long resourceId, @Param("no") String no);

}
