package com.juyss.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BaseServlet
 * @Desc:  Servlet基类,可以通过反射获取需要调用的方法,只需要获取到action的值,然后调用对应的方法
 * @package com.juyss.servlet
 * @project BookStore
 * @date 2020/8/11 13:35
 */
public abstract class BaseServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入BaseServlet的doPost()");
        String action = request.getParameter("action");
        System.out.println("获取到的方法名:" +action);
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入BaseServlet的doGet()");
        doPost(request, response);
    }
}
