package com.icepoint.framework.workorder.sys.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.icepoint.framework.workorder.sys.entity.TaskSetting;

public interface TaskSettingService {
	/**
	 * 添加系统通知设置
	 * @param entity 系统通知设置 {@linkplain com.icepoint.framework.workorder.sys.entity.TaskSetting TaskSetting}
	 * @return 系统通知设置 
	 */
	TaskSetting add(TaskSetting entity);
	
	/**
     * 编辑系统通知设置
     *
     * @param entity 编辑的系统通知设置 {@linkplain com.icepoint.framework.workorder.sys.entity.TaskSetting TaskSetting}
     * @return 返回编辑后的系统通知设置 {@linkplain com.icepoint.framework.workorder.sys.entity.TaskSetting TaskSetting}
     */
	TaskSetting edit(TaskSetting entity);
    
    /**
     * 删除系统通知设置
     *
     * @param ids 要删除的数据主键
     * @return 返回删除后的数据
     */
    Integer delete(List<Long> ids);

    /**
     * 列表查询系统通知设置
     *
     * @param entity 查询实体 {@linkplain com.icepoint.framework.workorder.sys.entity.TaskSetting TaskSetting}
     * @param pageable 分页对象
     * @return 所有系统通知设置 {@linkplain com.icepoint.framework.workorder.sys.entity.TaskSetting TaskSetting}
     */
    Page<TaskSetting> list(TaskSetting entity, Pageable pageable);
    
}
