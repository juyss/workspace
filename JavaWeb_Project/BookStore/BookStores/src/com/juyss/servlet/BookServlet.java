package com.juyss.servlet;

import com.juyss.pojo.Book;
import com.juyss.pojo.Page;
import com.juyss.service.BookService;
import com.juyss.service.impl.BookServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookServlet
 * @Desc: 图书管理页面
 * @package com.juyss.servlet
 * @project BookStore
 * @date 2020/8/12 11:23
 */
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入BookServlet的doGet()");
        super.doPost(request, response);
    }

    /**
     * 添加传入的书籍信息保存到数据库
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {

        Book book = new Book();
        BeanUtils.populate(book, request.getParameterMap());
        System.out.println("添加的图书信息" + book);
        Boolean flag = bookService.addBook(book);
        returnToMenu(flag, request, response);
    }

    /**
     * 删除书籍信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int i = Integer.parseInt(id);
        Boolean flag = bookService.deleteBookById(i);
        returnToMenu(flag, request, response);
    }

    /**
     * 更新书籍信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {

        Book book = new Book();
        BeanUtils.populate(book, request.getParameterMap());
        System.out.println("修改的图书信息" + book);
        Boolean flag = bookService.update(book);
        returnToMenu(flag, request, response);
    }

    /**
     * 获取单条书籍信息
     *
     * @param request
     * @param response
     */
    protected void queryBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int i = Integer.parseInt(id);
        Book book = bookService.queryBookById(i);
        request.setAttribute("book", book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    /**
     * 查询所有图书信息以集合返回数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void queryBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入BookServlet的queryBooks()");
        List<Book> books = bookService.queryBooks();
        System.out.println("查询结果:" + books);
        if (books != null) {
            request.setAttribute("bookList", books);
        }
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }


    /**
     * 分页显示数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void pageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = Integer.parseInt(request.getParameter("pageNo") == null ? "1" : request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize") == null ? Page.PAGE_SIZE.toString() : request.getParameter("pageSize"));
        System.out.println("pageNo-->"+pageNo);
        System.out.println("pageSize-->"+pageSize);


        if (pageNo>0) {
            Page<Book> page = bookService.pageBooks(pageNo, pageSize);
            page.setUrl("manager/bookServlet?action=pageBooks");
            System.out.println(page);

            request.setAttribute("page",page);

            request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
        }else{
            throw new RuntimeException("请求页码不存在!");
        }
    }

    /**
     * 设置提示信息同时返回书籍列表页
     *
     * @param flag     判断条件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void returnToMenu(Boolean flag, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (flag) {
            System.out.println("操作成功");
            request.setAttribute("errorMsg", "操作成功");
            if (request.getParameter("pageNo")!=null) {
                response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=pageBooks&errorMsg=succeed&pageNo=" + request.getParameter("pageNo"));
            }else{
                response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=pageBooks&errorMsg=succeed");
            }
        } else {
            System.out.println("操作失败");
            request.setAttribute("errorMsg", "操作失败");
            pageBooks(request, response);
        }
    }
}
