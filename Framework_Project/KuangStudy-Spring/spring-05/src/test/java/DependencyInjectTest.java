import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DependencyInjectTest
 * @Desc: 依赖注入测试
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/3 14:30
 */
public class DependencyInjectTest {

    //property注入
    @Test
    public void DI_Test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }

    //命名空间注入
    @Test
    public void NamespaceTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("user.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }
}
