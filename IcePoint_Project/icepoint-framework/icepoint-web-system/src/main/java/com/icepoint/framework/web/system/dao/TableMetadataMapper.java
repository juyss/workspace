package com.icepoint.framework.web.system.dao;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.TableMetadata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GenericTableMetadata)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-01 16:38:09
 */
@Mapper
public interface TableMetadataMapper extends RepositoryMapper<TableMetadata, Long> {

    List<TableMetadata> findAllByProjectId(
            @Param("tableInfo") TableInfo tableInfo,
            @Param("projectId") Long projectId);
}
