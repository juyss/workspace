package com.juyss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RouterController
 * @Desc: 路由跳转控制器
 * @package com.juyss.controller
 * @project atguigu-springboot
 * @date 2020/10/27 14:00
 */
@Controller
public class RouterController {

    @RequestMapping("/")
    public String Name(){

        return "index";
    }
}