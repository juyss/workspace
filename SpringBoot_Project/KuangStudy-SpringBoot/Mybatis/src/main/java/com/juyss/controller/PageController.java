package com.juyss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PageController
 * @Desc:
 * @package com.juyss.controller
 * @project KuangStudy-SpringBoot
 * @date 2020/10/24 11:03
 */
@Controller
public class PageController {



    @GetMapping("/")
    public String toIndex(){

        return "index";
    }

}
