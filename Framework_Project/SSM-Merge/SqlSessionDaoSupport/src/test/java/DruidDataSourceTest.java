import com.juyss.mapper.UserMapper;
import com.juyss.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DruidDataSourceTest
 * @Desc:
 * @package PACKAGE_NAME
 * @project Spring-Mybatis-Merge
 * @date 2020/9/10 19:54
 */
public class DruidDataSourceTest {

    @Test
    public void Test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
        UserMapper mapper = context.getBean("userMapperImpl", UserMapper.class);

        List<User> list = mapper.getUserList();
        System.out.println(list);
    }
}
