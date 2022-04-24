package com.springAPI.demo02;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DynamicProxyHandler02
 * @Desc: 通用动态代理处理类
 * @package com.springAPI.demo02
 * @project KuangStudy-Spring
 * @date 2020/9/4 15:35
 */
public class DynamicProxyHandler02 implements InvocationHandler {

    private Object target;

    public DynamicProxyHandler02(Object target) {
        this.target = target;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
