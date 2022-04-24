package com.juyss.controller;

import com.alibaba.fastjson.JSON;
import com.juyss.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: JsonController
 * @Desc: JSON测试类
 * @package com.juyss.controller
 * @project JavaWeb
 * @date 2020/9/13 13:58
 */
public class JsonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(15, "Jerry杰瑞");

        req.setAttribute("json", JSON.toJSONString(user));

        req.getRequestDispatcher("page/Json.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
