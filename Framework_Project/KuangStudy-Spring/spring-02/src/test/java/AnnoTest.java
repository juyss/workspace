import com.springAPI.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AnnoTest
 * @Desc: 测试类
 *          配置注解扫描,然后在需要添加的Bean类名上添加注解
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/1 20:18
 */
public class AnnoTest {

    @Test
    public void AnnoTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user.name);
    }
}
