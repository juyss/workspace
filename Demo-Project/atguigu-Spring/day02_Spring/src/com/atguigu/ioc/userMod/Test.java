package com.atguigu.ioc.userMod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.ioc.userMod.controller.UserController;
import com.atguigu.ioc.userMod.dao.UserDao;
import com.atguigu.ioc.userMod.dao.UserDaoImpl;
import com.atguigu.ioc.userMod.service.UserService;
import com.atguigu.ioc.userMod.service.UserServiceImpl;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("user.xml");
		UserController uc = ac.getBean("aaa", UserController.class);
		/*System.out.println(uc);
		UserService us = ac.getBean("userServiceImpl",UserServiceImpl.class);
		System.out.println(us);
		UserDao ud = ac.getBean("userDaoImpl",UserDaoImpl.class);
		System.out.println(ud);*/
		uc.addUser();
		
	}
	
}
