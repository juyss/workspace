package com.icepoint.framework.workflow.core.function;

import java.util.Map;

import com.mellisuga.core.InstanceManager;
import com.mellisuga.processing.IContext;
import com.mellisuga.processing.IPMDispatcher;
import com.mellisuga.processing.IPMParser;
import com.mellisuga.processing.IProcessingManager;


/**
 * 函数流程引擎执行
 * @author BD
 *
 */
public class FunFlowRunner {
	public static Map<String, Object> execute(String name,Map<String,String> para) {
		try{						
			Map<String,String> parameters = para;
			IProcessingManager _manager = InstanceManager.getInstance().processingManager();
				if (null == _manager){
					return null;
				}
				
				IPMParser parser = _manager.newParser();
				if (null == parser){
					return null;
				}
				
				IContext context = InstanceManager.getInstance().getContext();
				if (null == context){
					return null;
				}
				String root = (String)(context.getVariable("BaseDirectory"));	
							
				String mpath = root + "/config/models/" + name + ".xml";
				parser.setFile(mpath);
				IPMDispatcher pd = _manager.newDispatcher();
				if (null == pd){
					System.out.println("ModelExecuteServlet.doGet:PMDispatcher为空");
					return null;
				}
				pd.setModelParser(parser);
				pd.dispatch(null,parameters);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
}
