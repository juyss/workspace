package com.icepoint.framework.workflow.core.service;

import java.util.Map;
import com.icepoint.framework.plugin.workflow.IWorkflowPlugin;


public interface BpmService {
	
	/**
	 * 执行流程任务相关的动作
	 * 
	 * @param code 资产编码
	 * @param action 执行动作
	 * @param task 任务对象
	 * @param opinion 执行意见
	 * @return
	 */
	Map<String, Object> doTaskAction(String code,String action,Map<String, Object> task,String opinion);
	
}
