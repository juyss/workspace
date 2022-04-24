package proxy.dynamicProxy;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: Server
 * @Desc: 被代理类
 * @package proxy.dynamicProxy
 * @project DailyDemo
 * @date 2021/3/10 16:30
 */
public class Server implements Network{

    @Override
    public void browse() {
        System.out.println("真实的浏览器访问");
    }

}
