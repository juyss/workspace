package com.juyss.servlet;

import com.juyss.pojo.User;
import com.juyss.service.UserService;
import com.juyss.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServlet
 * @Desc:
 * @package com.juyss.servlet
 * @project DailyDemo
 * @date 2020/8/30 16:21
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        UserService userService = new UserServiceImpl();
        User user = userService.login(username);
        if (user!=null) {
            System.out.println("登陆成功!!!");
            resp.getWriter().write("登陆成功!!!");
        }else {
            System.out.println("登录失败!!!");
            resp.getWriter().write("登陆失败!!!");
        }
    }
}
