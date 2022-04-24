package com.atguigu.ioc.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		/**
		 * 若spring中有单例模式的bean，在初始化容器时九华创建此对象
		 * 而多例即原型的bean，会在使用时创建
		 */
		ApplicationContext ac = new ClassPathXmlApplicationContext("scope.xml");
		/*Student student1 = ac.getBean("student", Student.class);
		Student student2 = ac.getBean("student", Student.class);
		System.out.println(student1);
		System.out.println(student2);*/
		
	}
	
}
