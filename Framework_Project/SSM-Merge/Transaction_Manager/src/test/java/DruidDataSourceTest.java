import com.juyss.mapper.UserMapper;
import com.juyss.pojo.User;
import com.juyss.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DruidDataSourceTest
 * @Desc: 方法添加@Transactional注解表示当前方法为一次事务,遵循事务的ACID原则.
 * @package PACKAGE_NAME
 * @project Spring-Mybatis-Merge
 * @date 2020/9/10 19:54
 */
public class DruidDataSourceTest {

    @Test
    public void MapperTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
        UserMapper mapper = context.getBean("userMapper", UserMapper.class);

        List<User> list = mapper.getUserList();
        System.out.println(list);
    }

    @Test
    public void ServiceTxTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
        UserService userService = context.getBean("userService", UserService.class);

        userService.userOperation();

    }
}
