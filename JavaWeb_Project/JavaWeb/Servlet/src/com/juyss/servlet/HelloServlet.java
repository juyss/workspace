package com.juyss.servlet;


import javax.servlet.*;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: HelloServlet
 * @Desc: 实现Servlet接口编写servlet
 * @package com.juyss
 * @project JavaWeb
 * @date 2020/8/6 16:21
 */
public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
