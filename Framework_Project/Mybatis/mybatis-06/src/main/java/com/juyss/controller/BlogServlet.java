package com.juyss.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juyss.pojo.Blog;
import com.juyss.service.BlogService;
import com.juyss.service.impl.BlogServiceImpl;
import com.juyss.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BlogServlet
 * @Desc:
 * @package com.juyss.controller
 * @project Mybatis
 * @date 2020/9/18 16:22
 */
public class BlogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SqlSession sqlSession = null;
        try {

            sqlSession = MybatisUtils.getSqlSession();
            BlogService blogService = new BlogServiceImpl(sqlSession);

            PageHelper.startPage(request);
            List<Blog> blogs = blogService.showBlogs();
            System.out.println("博客列表====>"+blogs);
            PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
            System.out.println("页面信息====>"+pageInfo);

            request.setAttribute("blog_list", blogs);
            request.setAttribute("page_info", pageInfo);

            request.getRequestDispatcher("/WEB-INF/bloglist.jsp").forward(request,response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
                System.out.println("sqlSession关闭");
            }
        }
    }
}
