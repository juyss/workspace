package com.icepoint.framework.workorder.work.service;

import com.icepoint.framework.workorder.work.entity.WorkPlanTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 作业问题记录服务层
 *
 * @author 
 */
public interface WorkDefectService  {

    /**
     * 新增作业问题记录
     *
     * @param entity 新增的数据
     * @return 返回新增后的数据
     */
	Map<String,Object> add(Map<String,Object> entity);

    /**
     * 编辑作业问题记录
     *
     * @param entity 编辑的数据
     * @return 返回编辑后的数据
     */
	Map<String,Object> edit(Map<String,Object> entity);

    /**
     * 批量删除作业问题记录
     * 
     * @param ids 作业问题记录ID
     * @return
     */
	Integer delete(List<Long> ids);

	/**
	 * 分页查询作业问题记录
	 * @param filter 查询条件
	 * @param pageable 分页参数
	 * @return 返回分页查询列表
	 */
    Page<Map<String,Object>> list(Map<String, Object> filter, Pageable pageable);
    
    /**
     * 查询作业问题记录详情
     * @param id 作业问题记录ID
     * @return 返回详情数据
     */
    Map<String,Object> findMapById(Long id);

    /**
     * 问题上报
     * @author Juyss
     * @param entity
     * @return Map<String,Object>
     */
    Map<String, Object> problemReport(Map<String,Object> entity);
}
