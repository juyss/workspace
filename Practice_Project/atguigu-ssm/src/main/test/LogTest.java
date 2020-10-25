import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: LogTest
 * @Desc: 测试日志是否正常
 * @package PACKAGE_NAME
 * @project atguigu-ssm
 * @date 2020/10/17 21:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class LogTest {


    @Test
    public void Test(){
        Logger logger = Logger.getLogger(LogTest.class);
        logger.debug("=====================日志信息========================");
    }

}
