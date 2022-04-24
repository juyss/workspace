package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: sysFilter
 * @Desc:
 * @package com.filter
 * @project javaweb-filter
 * @date 2020/6/20 20:36
 */
public class sysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Object name = req.getSession().getAttribute("USER_NAME");
        System.out.println("过滤请求,name:"+name);
        if (name==null){
            resp.sendRedirect("/error.jsp");
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("Filter销毁");
    }
}
