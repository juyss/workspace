package com.juyss.controller;

import com.juyss.pojo.User;
import com.juyss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserController
 * @Desc: 处理用户请求
 * @package com.juyss.controller
 * @project SSM-Merge
 * @date 2020/9/20 15:10
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("user_session")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //处理用户登录请求
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        User user = null;
        try {
            user = userService.login(username); //根据用户名查询用户
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        if (user!=null){ //如果用户存在
            if (password.equals(user.getPassword())){ //判断密码是否正确
                model.addAttribute("user_session",user);
                return "user/login_success"; //密码正确,登陆成功
            }else { //密码错误,登录失败
                model.addAttribute("errorMsg", "密码错误!!!");
                return "user/login";
            }
        }else { //如果用户不存在,登录失败
            model.addAttribute("errorMsg", "用户不存在!!!");
            return "user/login";
        }
    }

    //处理用户注册请求
    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public String signin(User user,Model model){
        boolean flag = false;
        try {
            flag = userService.signin(user);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        if (flag){
            model.addAttribute("user_session",user);
            return "user/signin_success";
        }else {
            return "user/signin";
        }
    }

    //用户注销请求
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user_session");
        return "redirect:";
    }

    //GET请求,跳转登陆页面
    @RequestMapping("/login")
    public String toLogin(){
        return "user/login";
    }

    //GET请求,跳转到注册页面
    @RequestMapping("/signin")
    public String toSignin(){
        return "user/signin";
    }

}
