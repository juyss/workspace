package com.icepoint.framework.plugin.workflow;

import java.util.List;
import java.util.Map;

import org.pf4j.ExtensionPoint;

/**
 * 工作流插件接口
 * @author BD
 *
 */
public interface IWorkflowPlugin extends ExtensionPoint {

	/**
	 * 和工作流程定义中的type对应，用于区分工作流引擎类型
	 * @return
	 */
	Integer getType();
	
	/**
	 * 获取工作流列表
	 * 不包含详情，仅返回工作流信息
	 * 
	 * @return 工作流程列表  List<{@linkplain com.icepoint.framework.plugin.workflow.BpmFlow BpmFlow}>
	 */
	List<BpmFlow> getFlows();

	/**
	 * 获取工作流详情
	 * 获取所有工作流的描述，包含节点、连接关系，用于工作流同步
	 * 
	 * @param flowDefId 流程定义ID  
	 * @return 工作流程 {@linkplain com.icepoint.framework.plugin.workflow.BpmFlow BpmFlow}
	 */
	BpmFlow getFlow(String flowDefId);
	
	/**
	 * 新增或保存流程实例
	 * 
	 * @param flowDefId 流程定义ID  
	 * @param flowInstId 流程实例ID 
	 * @param data 流程业务数据  Map
	 * @param --id 记录主键  Long
	 * @param --appId 应用主键  Long
	 * @param --platformId 平台主键 Long 
	 * @param --ownerId 所有者标识  Long
	 * @param --createUserId 创建用户标识  Long
	 * @param --createTime 创建时间 Long
	 * 			时间格式yyMMddHHmmss
	 * @param --updateUserId 更新用户标识  Long
	 * @param --updateTime 更新时间  Long
	 * 			时间格式yyMMddHHmmss
	 * @param --deleted 逻辑删除标识 Boolean
	 * @param --... 其他参数
	 * @return 添加的流程实例
	 * @returnParam id 记录主键  Long
	 * @returnParam appId 应用主键 Long
	 * @returnParam platformId 平台主键  Long
	 * @returnParam ownerId 所有者标识 Long
	 * @returnParam createUserId 创建用户标识  Long
	 * @returnParam createTime 创建时间  Long
	 * 			时间格式yyMMddHHmmss
	 * @returnParam updateUserId 更新用户标识  Long
	 * @returnParam updateTime 更新时间  Long
	 * 			时间格式yyMMddHHmmss
	 * @returnParam deleted 逻辑删除标识  Boolean
	 * @returnParam ... 其他参数
	 * @request
	 * @response
	 * @table
	 */
	Map<String,Object> saveInstance(String flowDefId,String flowInstId,Map<String,Object> data);
	
	/**
	 * 获取流程任务相关数据
	 * 
	 * @param flowDefId String 流程定义ID 
	 * @param flowInstId String 流程实例ID 
	 * @return 流程任务相关数据 {@linkplain com.icepoint.framework.plugin.workflow.FlowData FlowData}
	 */
	FlowData getTaskData(String flowDefId,String flowInstId);
	
	//我的申请
	//myApply
	
	//我的待办
	
	//我的审批
	
	//任务指派
	
	//获取流程任务相关数据,获取任务的业务数据、表单、按钮、权限等信息，为了渲染展示任务页面
	
	//执行任务相关动作    执行任务相关的动作 如：同意，驳回，反对，锁定，解锁，转办，会签任务等相关操作
	
	//获取流程意见,通过流程实例ID 获取该流程实例下所有的审批意见、并按处理时间排序
	
}
