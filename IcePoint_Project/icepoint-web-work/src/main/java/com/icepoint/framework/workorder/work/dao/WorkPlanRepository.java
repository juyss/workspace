package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.workorder.work.entity.WorkPlan;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@Repository
public interface WorkPlanRepository extends LongStdRepository<WorkPlan> {
}
