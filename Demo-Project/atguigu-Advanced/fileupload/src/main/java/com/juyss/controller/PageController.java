package com.juyss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PageController
 * @Desc: 页面跳转控制器
 * @package com.juyss.controller
 * @project atguigu-Advanced
 * @date 2020/12/8 19:54
 */
@Controller
public class PageController {

    @RequestMapping({"/","/index"})
    public String rootPath(){
        return "index";
    }

    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/download")
    public String download(){

        return "download";
    }
}