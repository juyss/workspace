package com.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: filter
 * @Desc:
 * @package com.filter
 * @project javaweb-filter
 * @date 2020/6/20 17:09
 */
public class FilterTest implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("过滤前");
        chain.doFilter(request, response);
        System.out.println("请求已过滤");
    }

    @Override
    public void destroy() {
        System.out.println("Filter已摧毁");
    }
}
