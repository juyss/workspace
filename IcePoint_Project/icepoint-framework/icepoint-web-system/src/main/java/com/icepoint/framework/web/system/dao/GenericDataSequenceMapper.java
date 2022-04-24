package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.web.system.entity.GenericDataSequence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Jiawei Zhao
 */
@Mapper
public interface GenericDataSequenceMapper {

    /**
     * 执行解析后的sql，获取Sequence列表
     *
     * @param sql      解析后的sql
     * @param pageable 分页对象
     * @return Sequence列表
     */
    Page<GenericDataSequence> findAllBySql(@Param("sql") String sql, Pageable pageable);

    /**
     * 对新数据的Sequence记录进行必要的字段更新
     *
     * @param no         新数据的no
     * @param resourceId 资源id
     * @param objectId   数据id
     */
    void updateForNewDataByResourceIdAndObjectId(@Param("no") String no, @Param("resourceId") Long resourceId,
            @Param("objectId") Long objectId);
}
