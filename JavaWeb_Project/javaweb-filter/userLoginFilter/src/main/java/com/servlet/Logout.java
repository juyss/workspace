package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Logout
 * @Desc:
 * @package com.servlet
 * @project javaweb-filter
 * @date 2020/6/20 20:21
 */
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user_name = req.getSession().getAttribute("USER_NAME");
        if (user_name != null) {
            req.getSession().removeAttribute("USER_NAME");
        }
        resp.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
