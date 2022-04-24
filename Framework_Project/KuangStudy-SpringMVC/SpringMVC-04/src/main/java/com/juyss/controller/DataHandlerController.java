package com.juyss.controller;

import com.juyss.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DataHandlerController
 * @Desc: 数据处理
 * @package com.juyss.controller
 * @project KuangStudy-SpringMVC
 * @date 2020/9/12 18:33
 */
@Controller
@RequestMapping("/data")
public class DataHandlerController {

    @GetMapping("/same")
    public String test01(String name , Model model){
        model.addAttribute("message", name+"参数名一致");

        return "common";
    }


    @GetMapping("/different")
    public String test02(@RequestParam("username") String name , Model model){
        model.addAttribute("message", name+"参数名不一致");

        return "common";
    }

    @GetMapping("/object")
    public String test03(User user , Model model){

        model.addAttribute("message", "GET请求-->user对象:"+user);

        return "common";
    }

    @PostMapping("/object")
    public String test04(User user , Model model){

        model.addAttribute("message", "POST-->user对象:"+user);

        return "common";
    }



}
