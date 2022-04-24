package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.workorder.work.entity.WorkTask;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 
 */
@Repository
public interface WorkTaskRepository extends LongStdRepository<WorkTask> {
    /**
     * 查询计划打卡点
     * @param planId 计划id
     * @return
     */
    List<WorkTask> findAllByPlanId(Long planId);
}
