package com.juyss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: TestController
 * @Desc: Restful风格就是一个资源定位及资源操作的风格,使用`/`作为参数分隔符
 * @package com.juyss.controller
 * @project KuangStudy-SpringMVC
 * @date 2020/9/12 17:35
 */
@Controller
@RequestMapping("/restful")
public class RestfulController {

    //使用Restful风格处理URL
    @RequestMapping(value = "/{id}/{name}")
    public String Test02(@PathVariable Integer id, @PathVariable String name, Model model){

        model.addAttribute("message", "id:"+id+" name:"+name+"---GET请求:@RequestMapping");

        return "common";
    }

}
