import com.springAPI.pojo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AutowiredTest
 * @Desc: 注解自动注入
 *          - @Autowired 默认通过byType,然后在通过byName
 *              如果同一个Bean的注册id不唯一,可以通过@Qualifier(""),指定一个id装配
 *          - @Resource  默认通过byName,然后再通过byType
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/3 16:38
 */
public class AutowiredTest {

    @Test
    public void AutowiredTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }
}
