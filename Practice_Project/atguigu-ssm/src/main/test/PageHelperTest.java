import com.github.pagehelper.PageInfo;
import com.juyss.pojo.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PageHelperTest
 * @Desc: 使用Spring测试分页是否正确
 * @package PACKAGE_NAME
 * @project atguigu-ssm
 * @date 2020/9/27 15:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:spring/springMVC.xml"})
public class PageHelperTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    //初始化MockMvc
    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void PagehelperTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("page_num", "2")).andReturn();
        PageInfo info = (PageInfo) result.getRequest().getAttribute("page_info");
        System.out.println("========================"+info);
        List<Employee> list = info.getList();
        System.out.println("========================"+list);
        for (Employee employee : list) {
            System.out.println("========================"+employee);
        }
    }

}
