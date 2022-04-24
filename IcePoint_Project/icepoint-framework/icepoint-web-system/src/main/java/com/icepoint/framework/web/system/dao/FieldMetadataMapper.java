package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import org.apache.ibatis.annotations.Mapper;


/**
 * (FieldMetadata)表数据库访问层
 *
 * @author ck
 * @since 2021-05-19 14:26:07
 */
@Mapper
public interface FieldMetadataMapper extends RepositoryMapper<FieldMetadata,Long> {

}

