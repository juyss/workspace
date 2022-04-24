package com.juyss.servlet;

import com.juyss.pojo.Book;
import com.juyss.pojo.Page;
import com.juyss.service.BookService;
import com.juyss.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ClientServlet
 * @Desc: 前台图书操作
 * @package com.juyss.servlet
 * @project BookStore
 * @date 2020/8/19 15:17
 */
@SuppressWarnings("JavaDoc")
public class ClientServlet extends BaseServlet {

    private final BookService bookService = new BookServiceImpl();

    /**
     * 分页显示数据
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void pageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入了ClientServlet");

        int pageNo = Integer.parseInt(request.getParameter("pageNo") == null ? "1" : request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize") == null ? Page.PAGE_SIZE.toString() : request.getParameter("pageSize"));
        System.out.println("pageNo-->" + pageNo);
        System.out.println("pageSize-->" + pageSize);


        if (pageNo > 0) {
            Page<Book> page = bookService.pageBooks(pageNo, pageSize);
            page.setUrl("client/bookServlet?action=pageBooks");
            System.out.println(page);

            request.setAttribute("page", page);

            request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
        } else {
            throw new RuntimeException("请求页码不存在!");
        }
    }

    protected void pageBooksByPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pageNo = Integer.parseInt(request.getParameter("pageNo") == null ? "1" : request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize") == null ? Page.PAGE_SIZE.toString() : request.getParameter("pageNo"));
        System.out.println("min-->" + request.getParameter("min"));
        System.out.println("max-->" + request.getParameter("max"));

        //最小价格
        int min = Integer.parseInt(request.getParameter("min") == null || request.getParameter("min").equals("") ? "0" : request.getParameter("min"));

        //最大价格
        int max = Integer.MAX_VALUE;
        if (request.getParameter("max") != null && !request.getParameter("max").equals("")) {
            max = Integer.parseInt(request.getParameter("max"));
        }

        if (pageNo > 0) {
            Page<Book> page = bookService.pageBooksByPrice(pageNo, pageSize, min, max);
            StringBuilder sb = new StringBuilder("client/bookServlet?action=pageBooksByPrice&min=");
            sb.append(min);
            if (max != Integer.MAX_VALUE) {
                sb.append("&max=");
                sb.append(max);
            }
            page.setUrl(String.valueOf(sb));
            System.out.println(page);

            request.setAttribute("page", page);

            request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
        } else {
            throw new RuntimeException("请求页码不存在!");
        }

    }
}
