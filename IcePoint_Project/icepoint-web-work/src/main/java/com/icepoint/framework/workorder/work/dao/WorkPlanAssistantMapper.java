package com.icepoint.framework.workorder.work.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 作业计划协助人
 */
@Mapper
public interface WorkPlanAssistantMapper {

    /**
     * 批量删除
     * @author Juyss
     * @param ids id集合
     * @return Integer
     */
    Integer deleteBatch(List<Long> ids);

}
