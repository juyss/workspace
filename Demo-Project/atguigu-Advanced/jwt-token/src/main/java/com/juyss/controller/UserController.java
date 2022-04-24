package com.juyss.controller;

import com.juyss.pojo.User;
import com.juyss.service.UserService;
import com.juyss.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserController
 * @Desc:
 * @package com.juyss.controller
 * @project atguigu-Advanced
 * @date 2020/12/6 18:07
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String,String> login(@RequestParam("username")String username,
                                    @RequestParam("password")String password,
                                    HttpServletResponse response){

        HashMap<String, String> result = new HashMap<>();

        User user = userService.getUser(username);

        //返回用户为空,则说明此用户名信息不存在
        if (user==null){
            result.put("msg", "用户不存在");
            return result;
        }

        //判断密码是否正确
        if (!user.getPassword().equals(password)){
            result.put("msg", "密码错误");
            return result;
        }

        //验证通过
        HashMap<String, String> map = new HashMap<>();
        map.put("msg","success");
        map.put("username",username);
        map.put("role","admin");

        //生成token
        String token = JwtUtils.generateToken(map);

        //将token放入请求头
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);

        result.put("token", token);

        return result;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "请求成功!!!";
    }
}
