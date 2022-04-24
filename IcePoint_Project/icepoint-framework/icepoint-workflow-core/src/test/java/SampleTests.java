import com.icepoint.framework.workflow.SampleApplication;
import com.icepoint.framework.workflow.core.function.GetWorkflowPlugin;
import com.icepoint.framework.plugin.workflow.IWorkflowPlugin;
import com.icepoint.framework.web.system.dao.TableMetadataMapper;
import com.icepoint.framework.web.system.entity.TableMetadata;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Jiawei Zhao
 */
@ActiveProfiles("dev")
@SpringBootTest(classes = SampleApplication.class)
class SampleTests {

    @Autowired
    private TableMetadataMapper mapper;

    @Test
    void testMybatisPagination() {
        PageRequest request = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<TableMetadata> page = mapper.findAll(request);
        System.out.println(page);
    }

    @Test
    void testGetWorkflowPlugin() {
    	Map<String, Object>  map = GetWorkflowPlugin.execute(6);
    	IWorkflowPlugin plugin = (IWorkflowPlugin)map.get("plugin");
    	if (null != plugin){
    		System.out.println("找到插件!");
    	}
    	else{
    		System.out.println("未找到插件...");
    	}
    }
    
}
