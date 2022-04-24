package com.atguigu.proxy;

public class Test {

	public static void main(String[] args) {
		
		MathI math = new MathImpl();
		int result = math.div(1, 1);
		System.out.println(result);
		
	}
	
}
