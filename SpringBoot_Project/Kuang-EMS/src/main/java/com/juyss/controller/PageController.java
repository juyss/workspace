package com.juyss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PageController
 * @Desc:
 * @package com.juyss.controller
 * @project Kuang-EMS
 * @date 2020/10/23 13:55
 */
@Controller
public class PageController {

    /**
     * 处理登陆请求
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes reatt){
        System.out.println("进入了login()");
        System.out.println("获取参数:===>"+username+":"+password);

        if (!StringUtils.isEmpty(username) && "102850".equals(password)){
            return "redirect:/dashboard";
        }else {
            reatt.addAttribute("msg", "用户名或密码错误");
            return "redirect:/toIndex";
        }
    }

    /**
     * 跳转到登录页
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex(@RequestParam("msg") String msg,
                          Model model){
        model.addAttribute("msg", msg);
        return "index";
    }

    /**
     * 跳转到图表页面
     * @return
     */
    @RequestMapping("/dashboard")
    public String dashboard(){

        return "dashboard";
    }
}
