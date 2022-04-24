package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.workorder.work.entity.CheckPoint;

import java.util.List;

import com.icepoint.framework.workorder.work.entity.WorkDefect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 
 */
@Mapper
public interface CheckPointMapper  extends RepositoryMapper<CheckPoint, Long> {
	/**
	 *
	 * @param assetDefId
	 * @param objId
	 * @return
	 */
	List<CheckPoint> findCheckPoints(Long assetDefId,Long objId);


}
