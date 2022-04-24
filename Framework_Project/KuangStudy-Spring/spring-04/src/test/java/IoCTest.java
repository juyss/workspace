import com.springAPI.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: IoCTest
 * @Desc: 实例化ApplicationContext时,解析xml文件,通过bean的无参构造器创建已经注册的bean的实例,如果有参数,则通过对应的set方法进行参数注入.
 *        调用getBean()方法时,直接从容器中取出指定id的对象,且此对象只有一个引用.(默认为单例模式)
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/2 22:21
 */
public class IoCTest {

    @Test
    public void Test01(){
        System.out.println("创建context对象前");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("创建context对象后");
        User user = applicationContext.getBean("user", User.class);
        System.out.println("获取Bean对象");
        user.show();
        System.out.println("调用show()方法");
    }

    @Test
    public void Test02(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        User user01 = applicationContext.getBean("user", User.class);
        User user02 = applicationContext.getBean("user", User.class);
        User user03 = applicationContext.getBean("user", User.class);
        System.out.println(user01==user02); //true
        System.out.println(user01==user03); //true
        System.out.println(user03==user02); //true

    }
}
