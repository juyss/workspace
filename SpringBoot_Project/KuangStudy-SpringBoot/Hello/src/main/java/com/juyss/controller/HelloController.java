package com.juyss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: HelloController
 * @Desc: quick start
 * @package com.juyss.controller
 * @project Spring-Boot
 * @date 2020/10/22 17:22
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String Name(){

        return "你好,世界!!!";
    }
}
