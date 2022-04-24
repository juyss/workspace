package com.juyss.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: LoginController
 * @package com.juyss.controller
 * @project Spring-Security
 * @date 2021/12/16 0:26
 */
@RestController
@Slf4j
public class LoginController {

    @RequestMapping("main")
    public String main(){
        log.info("跳转主页");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return String.format("%s 登陆成功", principal);
    }

    @RequestMapping("failure")
    public String error(){
        log.info("登陆失败");
        return "登陆失败";
    }
}
