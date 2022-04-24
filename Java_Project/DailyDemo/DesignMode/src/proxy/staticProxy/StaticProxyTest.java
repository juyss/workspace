package proxy.staticProxy;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: StatisProxyTest
 * @Desc:  代理模式,不直接通过`被代理类对象`调用方法,而是通过`代理类`调用`被代理类的方法`
 * @package proxy
 * @project DailyDemo
 * @date 2020/7/27 15:14
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Network network = new Server();
        ProxyServer proxyServer = new ProxyServer(network);
        proxyServer.browse();
    }
}

interface Network {
    void browse();
}

//被代理类
class Server implements Network {
    @Override
    public void browse() {
        System.out.println("真实的浏览器访问");
    }
}

//代理类
class ProxyServer implements Network {
    private Network network;

    public ProxyServer(Network network){
        this.network = network;
    }

    private void check(){
        System.out.println("链接检查");
    }

    @Override
    public void browse() {
        check();
        System.out.println("代理浏览器访问");
        network.browse();
    }
}
