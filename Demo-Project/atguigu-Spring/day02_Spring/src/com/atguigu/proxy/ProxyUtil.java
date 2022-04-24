package com.atguigu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {

	private MathImpl mathImpl;
	
	public Object getProxy() {
		ClassLoader loader = this.getClass().getClassLoader();
		Class[] interfaces = mathImpl.getClass().getInterfaces();
		return Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return method.invoke(mathImpl, args);
			}
		});
	}
	
}
