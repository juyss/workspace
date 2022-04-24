package com.icepoint.framework.workflow.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icepoint.framework.plugin.workflow.IWorkflowPlugin;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.workflow.core.service.BpmService;


/**
 * 工作流
 */
@RestController
@RequestMapping("bpm")
public class BpmController {
    @Resource
    private BpmService service;

	//我的申请
	
	//我的待办
	
	//我的审批
	
	//任务指派
	
	/**
	 * 执行流程任务相关的动作
	 * 
	 * @param code 资产编码
	 * @param action 执行动作
	 * @param task 任务对象
	 * @param opinion 执行意见
	 * @return
	 */
	@PostMapping("doTaskAction")
    public Response<Map<String, Object>> doTaskAction(@RequestParam("code")String code,String action,Map<String, Object> task,String opinion) {
        return ResponseUtils.one(service.doTaskAction(code,action,task,opinion));
    }
	

	
	//获取流程任务相关数据,获取任务的业务数据、表单、按钮、权限等信息，为了渲染展示任务页面
	
	//执行任务相关动作    执行任务相关的动作 如：同意，驳回，反对，锁定，解锁，转办，会签任务等相关操作
	
	//获取流程意见,通过流程实例ID 获取该流程实例下所有的审批意见、并按处理时间排序
	
}
