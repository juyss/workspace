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
 * @ClassName: PageController
 * @Desc: 控制层
 * @package com.juyss.controller
 * @project KuangStudy-SpringMVC
 * @date 2020/9/11 21:35
 */
@Controller
public class PageController {

    /**
     * 请求地址为:ip:port/ProjectName/show
     *
     * @param model 传递的模型,可以处理一些数据
     * @return 需要视图处理器解析的视图名
     */
    @RequestMapping("/show")
    public String showMsg(Model model){

        ApplicationContext context = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        User user = context.getBean("user", User.class);
        user.setId(1);
        user.setName("Morty");

        //设置模型属性数据,传递给视图层
        model.addAttribute("massage",user);

        //解析视图名
        return "pagename";
    }

}
