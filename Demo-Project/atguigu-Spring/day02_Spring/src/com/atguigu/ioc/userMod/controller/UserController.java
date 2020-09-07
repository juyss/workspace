package com.atguigu.ioc.userMod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atguigu.ioc.userMod.service.UserService;

/**
 * 基于注解的组件化管理：
 * @Component,@Controller（控制层）,@Service（业务层）,@Repository（持久层）
 * 以上四个注解功能完全相同，不过在实际开发中，要在实现不同功能的类上加上相应的注解
 * 完成组件化管理的过程：
 * 1、在需要被spring管理的类上加上相应注解
 * 2、在配置文件中通过<context:component-scan>对所设置的包结构进行扫描，就会将加上注解的类，作为spring的组件进行加载
 * 组件：指spring中管理的bean
       作为spring的组件进行加载：会自动在spring的配置文件中生成相对应的bean，这些bean的id会以类的首字母小写为值；
       也可以通过@Controller("beanId")为自动生成的bean指定id
 * 自动装配：在需要赋值的非字面量属性上，加上@Autowired，就可以在spring容器中，通过不同的方式匹配到相对应的bean
 * @Autowired装配时，会默认使用byType的方式，此时要求spring容器中只有一个能够为其赋值
 * 当byType实现不了装配时，会自动切换到byName，此时要求spring容器中，有一个bean的id和属性名一致
 * 若自动装配时，匹配到多个能够复制的bean，可使用@Qualifier(value="beanId")指定使用的bean
 * @Autowired和@Qualifier(value="beanId")可以一起作用域一个带形参的方法上，此时，@Qualifier(value="beanId")
 * 所指定的bean作用于形参
 *
 */



//@Controller(value="aaa")
@Controller("aaa")
public class UserController {

	@Autowired
	private UserService userService;
	
	public void addUser() {
		userService.addUser();
	}
	
	public UserController() {
		System.out.println("UserController");
	}
	
}
