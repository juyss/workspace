package com.juyss.controller;

import com.juyss.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserController
 * @Desc: 控制层
 * @package com.juyss.controller
 * @project KuangStudy-SpringMVC
 * @date 2020/9/11 23:32
 */
@Controller
public class UserController {

    @RequestMapping("/show")
    private String showMessage(Model model){

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-web.xml");
        User user = context.getBean("user", User.class);
        user.setName("Rick");
        user.setId(1);

        model.addAttribute("message", user);

        return "jsp_name";
    }

}
