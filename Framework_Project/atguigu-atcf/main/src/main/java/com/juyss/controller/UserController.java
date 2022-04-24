package com.juyss.controller;

import com.juyss.bean.TAdmin;
import com.juyss.service.UserService;
import com.juyss.util.Const;
import com.juyss.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserController
 * @Desc: 用户Controller,处理与用户相关的请求
 * @package com.juyss.controller
 * @project atguigu-CrowdFunding
 * @date 2020/10/11 14:53
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * 处理用户登录请求
     * @param loginacct 用户名
     * @param userpswd 密码
     * @param model 数据模型
     * @param session session对象
     * @return 返回到后台管理页面 main.jsp
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String loginacct,
                        @RequestParam("password")String userpswd,
                        Model model,
                        HttpSession session){
        log.debug("进入login(),获取参数loginacct==>{},userpswd==>{}",loginacct,userpswd);
        HashMap<String, Object> map = new HashMap<>();

        map.put("loginacct", loginacct);

        log.debug("Map集合内容：{}",map);

        TAdmin LoginUser = service.getUserByMap(map);

        //判断用户是否存在
        if (LoginUser == null){
            log.debug("登陆失败信息==>{}",Const.LOGIN_LOGINACCT_ERROR);
            model.addAttribute(Const.ERROR_MSG, Const.LOGIN_LOGINACCT_ERROR);

            return "forward:/toLogin";
        }

        //对传入的密码进行加密处理
        String MD5pwd = MD5Util.digest(userpswd);

        //判断密码是否正确
        if (!MD5pwd.equals(LoginUser.getUserpswd())){
            log.debug("登陆失败信息==>{}",Const.LOGIN_USERPSWD_ERROR);
            model.addAttribute(Const.ERROR_MSG, Const.LOGIN_USERPSWD_ERROR);

            return "forward:/toLogin";
        }

        //将正确的用户信息存入Session中
        session.setAttribute(Const.LOGIN_ADMIN, LoginUser);
        log.debug("登陆成功信息==>session信息:{}",LoginUser);

        //重定向到管理员主页面
        return "redirect:/toMain";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(Const.LOGIN_ADMIN);
        log.debug("登出成功,返回主页面==>session已销毁");
        return "redirect:/toIndex";
    }


    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public String signin(){
        log.debug("进入signin()");

        return "redirect:/toLogin";
    }

}
