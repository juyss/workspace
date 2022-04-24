package com.icepoint.framework.plugin.workflow;

import java.util.List;
import java.util.Map;

/**
 * 流程实例节点描述数据
 * @author BD
 *
 */
public class FlowData {
	
	/**
	 * 流程当前节点按钮信息
	 */
	List<BpmButton> btnList;
	
	//data 流程自定义表单业务数据
	
	/**
	 * 流程定义ID
	 */
	String flowDefId;
	
	//BpmForm form;//流程当前节点表单 	
	//initData 流程自定义表单 初始化数据，可用于子表数据复制赋值 
	
	/**
	 * 流程自定义表单 字段权限
	 */
	Map<String,Object> permission;
	
	//tablePermission 流程自定义表单 表权限 	< string, object > map
}
