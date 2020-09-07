import com.springAPI.staticproxy.Agent;
import com.springAPI.staticproxy.Landlord;
import org.junit.Test;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: StaticProxyTest
 * @Desc: 静态代理测试
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/7 19:47
 */
public class StaticProxyTest {

    @Test
    public void Test(){
        Landlord landlord = new Landlord();

        Agent agent = new Agent(landlord);

        agent.RentOut();
    }
}
