package com.juyss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RouterController
 * @Desc:
 * @package com.juyss.controller
 * @project KuangStudy-SpringBoot
 * @date 2020/10/25 15:47
 */
@RestController
public class RouterController {

    @GetMapping("/")
    public String index(){

        return "Hello,World!!!";
    }

    @PostMapping("/login")
    public String postLogin(){

        return "postLogin";
    }

    @GetMapping("/login")
    public String getLogin(){

        return "getLogin";
    }

}
