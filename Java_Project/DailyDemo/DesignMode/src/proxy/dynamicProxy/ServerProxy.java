package proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: ServerProxy
 * @Desc: 代理类
 * @package proxy.dynamicProxy
 * @project DailyDemo
 * @date 2021/3/10 16:32
 */
public class ServerProxy implements InvocationHandler {

    private Network target;

    public ServerProxy(Network target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("通过代理服务访问");
        Object result = method.invoke(target, args);
        System.out.println("通过代理服务访问");
        return result;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

}
