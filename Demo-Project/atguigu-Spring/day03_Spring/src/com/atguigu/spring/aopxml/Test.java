package com.atguigu.spring.aopxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop-xml.xml");
		MathI math = ac.getBean("mathImpl", MathI.class);
		int add = math.add(12, 12);
		System.out.println(add);
	}
	
}
