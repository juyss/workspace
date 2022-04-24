import com.springAPI.config.SpringConfig;
import com.springAPI.pojo.Admin;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ConfTest
 * @Desc:
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/1 21:19
 */
public class ConfTest {

    @Test
    public void Test(){
        //配置类包扫描到的Admin
        ApplicationContext context01 = new AnnotationConfigApplicationContext(SpringConfig.class);
        Admin admin01 = context01.getBean("admin", Admin.class);
        System.out.println(admin01);

        //通过@Bean的注解方法获得的
        ApplicationContext context02 = new AnnotationConfigApplicationContext(SpringConfig.class);
        Admin admin02 = context02.getBean("admin", Admin.class);
        System.out.println(admin02);
        System.out.println(admin01==admin02);
    }

}
