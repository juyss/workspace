package com.github.tangyi.pub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pub")
public class TestController {
    @GetMapping("/test/hello")
    public Object test1() {
        return "hello world";
    }



}
