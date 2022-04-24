package com.icepoint.framework.plugin.workflow;

import java.util.List;
import java.util.Map;

import org.pf4j.Extension;

@Extension
public class WorkflowAgilebpmPlugin implements IWorkflowPlugin {
	@Override
	public Integer getType() {
		return 6;
	}
	
	@Override
	public List<BpmFlow> getFlows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BpmFlow getFlow(String flowDefId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> saveInstance(String flowDefId, String flowInstId, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlowData getTaskData(String flowDefId, String flowInstId) {
		// TODO Auto-generated method stub
		return null;
	}

}
