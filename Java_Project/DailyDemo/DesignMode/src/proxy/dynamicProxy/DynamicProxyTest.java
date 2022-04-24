package proxy.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: DynamicProxyTest
 * @Desc:
 * @package proxy.dynamicProxy
 * @project DailyDemo
 * @date 2021/3/10 17:07
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        Network target = new Server();

        ServerProxy proxy = new ServerProxy(target);

        Network proxyInstance = (Network) proxy.getProxyInstance();

        proxyInstance.browse();
    }
}
