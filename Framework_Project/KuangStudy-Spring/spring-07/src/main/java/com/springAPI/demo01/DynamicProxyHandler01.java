package com.springAPI.demo01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DynamicProxyHandler02
 * @Desc:  动态代理处理器
 * @package com.springAPI.demo01
 * @project KuangStudy-Spring
 * @date 2020/9/4 13:43
 */
public class DynamicProxyHandler01 implements InvocationHandler {

    private Rent rent;

    public DynamicProxyHandler01(Rent rent) {
        this.rent = rent;
    }

    /**
     * 获得代理对象
     * @return 代理类实例
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(rent, args);
        return result;
    }
}
