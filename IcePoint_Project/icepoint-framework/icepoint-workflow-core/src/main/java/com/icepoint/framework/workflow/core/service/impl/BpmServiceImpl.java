package com.icepoint.framework.workflow.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;

import com.icepoint.framework.plugin.workflow.BpmFlow;
import com.icepoint.framework.plugin.workflow.IWorkflowPlugin;
import com.icepoint.framework.web.core.message.CodedMessageException;
import com.icepoint.framework.web.core.message.DataNotFoundMessageException;
import com.icepoint.framework.web.core.message.NullArgumentMessageException;
import com.icepoint.framework.web.core.support.ServiceSupport;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.util.SecurityUtils;
import com.icepoint.framework.workflow.core.dao.BpmFlowRepository;
import com.icepoint.framework.workflow.core.function.GetWorkflowPlugin;
import com.icepoint.framework.workflow.core.service.BpmService;

@Service
public class BpmServiceImpl extends ServiceSupport implements BpmService {
	@Autowired
    private BpmFlowRepository bpmFlowRepository;
	/**
	 * 插件缓存，加载一次即可
	 * 用  appId+platformId+工作流类型  作为key进行缓存
	 */
    private Map<String,IWorkflowPlugin> plugins = new HashMap<String,IWorkflowPlugin>();
    
	@Override
	public Map<String, Object> doTaskAction(String code, String action, Map<String, Object> task, String opinion) {
		if (StringUtils.isEmpty(code)){
			throw new NullArgumentMessageException("code");
		}
		
		User user = SecurityUtils.getRequiredUser();
		BpmFlow flow = new BpmFlow();
		flow.setCode(code);
		flow.setAppId(user.getAppId());
		flow.setPlatformId(user.getPlatformId());
		List<BpmFlow> flows = bpmFlowRepository.findAll(Example.of(flow));
		if (flows.size() <= 0){
			throw new DataNotFoundMessageException();
		}
		
		flow = flows.get(0);
		String pluginKey = String.format("WFPLUNIN_%d_%d,",user.getAppId(),user.getPlatformId());
		IWorkflowPlugin plugin = null;
		if (plugins.containsKey(pluginKey)){
			plugin = plugins.get(plugins);
		}
		else{
			Map<String, Object> ret = GetWorkflowPlugin.execute(flow.getType());
			plugin = (IWorkflowPlugin)ret.get("plugin");
			if (null == plugin){
				throw new CodedMessageException("404");
			}
		}
		
		//plugin.doTaskAction
		return null;
	}

}
