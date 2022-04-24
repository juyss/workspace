package com.icepoint.framework.workflow.core.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import com.icepoint.framework.plugin.workflow.IWorkflowPlugin;

public class GetWorkflowPlugin {
	public static Map<String, Object> execute(Integer type) {
		PluginManager pluginManager = new DefaultPluginManager();

        List<IWorkflowPlugin> plugins = pluginManager.getExtensions(IWorkflowPlugin.class);
        IWorkflowPlugin plugin = plugins.stream().filter(item -> type == item.getType()).findAny().orElse(null);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("plugin", plugin);
        return result;
	}
}
