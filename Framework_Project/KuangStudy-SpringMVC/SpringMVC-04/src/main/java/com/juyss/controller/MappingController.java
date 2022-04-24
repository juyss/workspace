package com.juyss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MappingController
 * @Desc: @PostMapping 和 @GetMapping 测试
 * @package com.juyss.controller
 * @project KuangStudy-SpringMVC
 * @date 2020/9/12 18:13
 */
@Controller
@RequestMapping("/mapping")
public class MappingController {


    @PostMapping("/test")
    public String Test01(Integer id, String name, Model model){

        model.addAttribute("message", "id:"+id+" name:"+name+"---Post请求:@PostMapping");

        return "common";
    }

    @GetMapping("/test")
    public String Test02(Integer id, String name, Model model){

        model.addAttribute("message", "id:"+id+" name:"+name+"---GET请求:@GetMapping");

        return "common";
    }
}
