package com.atguigu.spring.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("scope.xml");
		Emp emp1 = ac.getBean("emp", Emp.class);
		Emp emp2 = ac.getBean("emp", Emp.class);
		System.out.println(emp1.equals(emp2));
		
	}
	
}
