import com.springAPI.demo01.Rent;
import com.springAPI.demo01.DynamicProxyHandler01;
import com.springAPI.demo01.Host;
import com.springAPI.demo02.DynamicProxyHandler02;
import com.springAPI.demo02.Husband;
import com.springAPI.demo02.WeddingCompany;
import org.junit.Test;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DynamicProxyTest
 * @Desc:
 * @package PACKAGE_NAME
 * @project KuangStudy-Spring
 * @date 2020/9/4 14:06
 */
public class DynamicProxyTest {

    /**
     * 针对于Rent接口
     */
    @Test
    public void RentTest(){
        //创建真实的被代理类对象
        Host host = new Host();
        //实例化代理类处理器,将被代理类对象传入
        DynamicProxyHandler01 handler = new DynamicProxyHandler01(host);
        //调用getProxy(),获取代理类对象
        Rent proxy = (Rent) handler.getProxy();
        //调用目标方法
        proxy.rent();
    }

    /**
     * 通用方法
     */
    @Test
    public void MarriedTest(){

        Husband husband = new Husband();

        DynamicProxyHandler02 handler = new DynamicProxyHandler02(husband);

        WeddingCompany proxy = (WeddingCompany) handler.getProxy();

        proxy.getMarried();
    }
}
