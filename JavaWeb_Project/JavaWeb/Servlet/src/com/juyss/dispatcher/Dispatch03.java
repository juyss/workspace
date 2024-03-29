package com.juyss.dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ${NAME}
 * @Desc:
 * @package ${PACKAGE_NAME}
 * @project JavaWeb
 * @date 2020/8/7 1:01
 */
public class Dispatch03 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
        request.getRequestDispatcher("first/second/4.html").forward(request,response);
    }
}
