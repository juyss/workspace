package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.workorder.work.entity.CheckPoint;
import com.querydsl.core.types.Predicate;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author 
 */
@Repository
public interface CheckPointRepository extends LongStdRepository<CheckPoint> {
	
}
