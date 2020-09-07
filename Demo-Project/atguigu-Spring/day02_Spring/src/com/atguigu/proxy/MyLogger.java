package com.atguigu.proxy;

public class MyLogger {

	public static void before(String methodName, String args) {
		System.out.println("method:"+methodName+",arguments:"+args);
	}
	
	public static void after(String methodName, Object reslut) {
		System.out.println("method:"+methodName+",reslut:"+reslut);
	}
	
	public static void throwing() {
		System.out.println("有异常了。。。。");
	}
	
}
