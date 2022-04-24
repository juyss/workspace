package com.juyss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RouterController
 * @Desc: 路由控制
 * @package com.juyss.controller
 * @project KuangStudy-SpringBoot
 * @date 2020/10/24 13:05
 */
@Controller
public class RouterController {

    @RequestMapping("/")
    public String index(){
        System.out.println("`/` ==> /index.html");
        return "index";
    }

    @RequestMapping("/index")
    public String toIndex(){
        System.out.println("`/index` ==> /index.html");
        return "index";
    }

    @RequestMapping("/login")
    public String toLogin(){
        System.out.println("/toLogin ==> /views/Login.html页面");
        return "views/login";
    }

    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id")String id){
        System.out.println("跳转到views/level1/"+id);
        return "views/level1/"+id;
    }

    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id")String id){
        System.out.println("跳转到views/level2/"+id);
        return "views/level2/"+id;
    }

    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id")String id){
        System.out.println("跳转到views/level3/"+id);
        return "views/level3/"+id;
    }

}
