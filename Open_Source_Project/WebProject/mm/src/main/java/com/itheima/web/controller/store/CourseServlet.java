package com.itheima.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Course;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zxq
 * @create 2020-08-28 17:38
 */
@WebServlet("/store/course")
public class CourseServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //获取operation的类型
        String operation = request.getParameter("operation");

        //功能分发
        if("list".equals(operation)){
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }
    }



    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //默认设置
        int page = 1;
        int size= 5;
        //接收来自客户端的设置
        if(StringUtils.isNoneBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNoneBlank(request.getParameter("size"))){
            size = Integer.parseInt(request.getParameter("size"));
        }

        //调用业务层处理业务
        PageInfo all = courseService.findAll(page,size);
        //将数据保存到指定的位置
        request.setAttribute("page",all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/course/list.jsp").forward(request,response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/course/add.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取数据，封装为对象
        Course course = BeanUtil.fillBean(request,Course.class,"yy-MM-dd");

        //2.调用业务层接口save
        courseService.save(course);

        //3.跳转到list页面
        response.sendRedirect(request.getContextPath()+"/store/course?operation=list");

    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        //查询要修改的数据findById
        Course course = courseService.findById(id);
        //将数据加载到指定区域，供页面获取
        request.setAttribute("course",course);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/course/update.jsp").forward(request,response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取数据，将数据封装成对象
        Course course = BeanUtil.fillBean(request,Course.class);

        //2.调用业务层接口
        courseService.update(course);

        //3.跳转到list页面
        response.sendRedirect(request.getContextPath()+"/store/course?operation=list");


    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //1.获取数据，将数据封装成对象
        Course course = BeanUtil.fillBean(request, Course.class);

        //2.调用业务层接口
        courseService.delete(course);

        //3.跳到list页面
        response.sendRedirect(request.getContextPath()+"/store/course?operation=list");

    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
