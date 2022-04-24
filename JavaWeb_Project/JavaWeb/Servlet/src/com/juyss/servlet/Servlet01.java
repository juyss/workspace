package com.juyss.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ${NAME}
 * @Desc:  测试多选框结果
 * @package ${PACKAGE_NAME}
 * @project JavaWeb
 * @date 2020/8/6 22:37
 */
public class Servlet01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入了doPost()方法");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入了doGet()方法");

        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        String[] hobbies = request.getParameterValues("hobbies");


        System.out.println(userName);
        System.out.println(pwd);
        System.out.println(hobbies);

        //如果前端页面什么都没勾选,会报NullPointerException,需提前判断
        System.out.println("asList()-->"+Arrays.asList(hobbies));

        //资源重定向,URL会变
        response.sendRedirect("1.html"); //相对路径
        response.sendRedirect("/Servlet/index.jsp"); //绝对路径
        //转发,URL不变
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
