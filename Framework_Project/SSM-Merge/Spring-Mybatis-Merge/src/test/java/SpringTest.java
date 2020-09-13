import com.juyss.mapper.UserMapper;
import com.juyss.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SpringTest
 * @Desc: Spring测试类
 * @package PACKAGE_NAME
 * @project Spring-Mybatis-Merge
 * @date 2020/9/10 16:57
 */
public class SpringTest {

    @Test
    public void Test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
        UserMapper mapper = context.getBean("userMapperImpl", UserMapper.class);

        List<User> users = mapper.getUserList();

        System.out.println(users);

        User user = mapper.getUserById(5);
        System.out.println(user);
    }
}
