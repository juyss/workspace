package com.juyss.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AdminController
 * @Desc:
 * @package com.juyss.controller
 * @project Spring-Security
 * @date 2020/10/19 20:12
 */
@Controller
public class AdminController {

    private final Logger log = Logger.getLogger(AdminController.class);

    @RequestMapping("/toMain")
    public String toMain(){
        log.debug("登陆成功，准备跳转到主页面");
        return "main";
    }

    @RequestMapping("/unauth")
    public String unAuth(){
        log.debug("跳转到无权限页面");
        return "unauth";
    }

}
