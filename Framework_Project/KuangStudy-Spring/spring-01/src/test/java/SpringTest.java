import com.springAPI.pojo.Hello;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SpringTest
 * @Desc: 测试类
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/1 11:22
 */
public class SpringTest {

    @Test
    public void HelloTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Hello bean = (Hello) context.getBean("hello");
        bean.show();
    }
}
