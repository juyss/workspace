package com.atguigu.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop.xml");
		MathI math = ac.getBean("mathImpl", MathI.class);
		System.out.println(math.getClass().getName());
		int i = math.div(4, 1);
		System.out.println(i);
		
	}
	
}
