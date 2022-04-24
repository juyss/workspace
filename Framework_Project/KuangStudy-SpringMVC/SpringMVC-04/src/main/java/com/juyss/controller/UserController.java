package com.juyss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserController
 * @Desc: 基本项目搭建
 * @package com.juyss.controller
 * @project KuangStudy-SpringMVC
 * @date 2020/9/12 17:16
 */
@Controller
public class UserController {

    @RequestMapping(value = "/show")
    public String showMsg(Model model){

        model.addAttribute("message", "showMsg()方法");

        return "common";
    }

}
