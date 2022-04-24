package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.workorder.work.entity.WorkTaskClock;

import org.springframework.stereotype.Repository;

/**
 * @author 
 */
@Repository
public interface WorkTaskClockRepository extends LongStdRepository<WorkTaskClock> {
	
}
