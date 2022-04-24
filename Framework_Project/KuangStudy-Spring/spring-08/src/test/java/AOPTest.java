import com.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AOPTest
 * @Desc: AOP测试类
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/4 21:45
 */
public class AOPTest {

    /**
     * Spring接口API实现
     */
    @Test
    public void Test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);

        userService.insert();
        userService.delete();
        userService.update();
        userService.select();
    }
}
