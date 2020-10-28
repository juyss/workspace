package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zxq
 * @create 2020-08-26 8:54
 */
@WebServlet("/system/dept")
public class DeptServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求方法的类型
        String operation = request.getParameter("operation");

        //分发请求
        if ("list".equals(operation)) {
            this.list(request,response);
        } else if ("toAdd".equals(operation)) {
            this.toAdd(request,response);
        } else if ("save".equals(operation)) {
            this.save(request,response);
        } else if ("toEdit".equals(operation)) {
            this.toEdit(request,response);
        } else if ("edit".equals(operation)) {
            this.edit(request,response);
        } else if ("delete".equals(operation)) {
            this.delete(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //默认设置
        int page = 1;
        int size = 5;
        //接收来自客户端的设置
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            size = Integer.parseInt(request.getParameter("size"));
        }

        //调用业务层处理业务
        PageInfo all = deptService.findAll(page, size);

        //将获取到的数据传给页面
        request.setAttribute("page",all);

        //跳转页面（转发）
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(request,response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加载所有的部门信息放入到add页面的deptList中
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);

        //跳转页面（转发）
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取数据，封装成对象
        Dept dept = BeanUtil.fillBean(request, Dept.class, "yyyy-MM-dd");

        //2.调用业务层接口，保存到数据库
        deptService.save(dept);

        //3.跳转页面（重定向）
        response.sendRedirect(request.getContextPath()+"/system/dept?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要修改的部门的id
        String id = request.getParameter("id");

        //将要修改的部门的信息展示出来
        Dept dept = deptService.findById(id);
        //加载所有的部门信息放到update页面的deptList中
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);

        //加载要修改的部门的信息放到update页面的dept中
        request.setAttribute("dept",dept);

        //跳转页面（转发）
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据，封装对象
        Dept dept = BeanUtil.fillBean(request, Dept.class);

        //调用业务层处理业务
        deptService.update(dept);

        //跳转页面（重定向）
        response.sendRedirect(request.getContextPath()+"/system/dept?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据，封装对象
        Dept dept = BeanUtil.fillBean(request, Dept.class);

        //调用业务层处理业务
        deptService.delete(dept);

        //跳转页面（重定向）
        response.sendRedirect(request.getContextPath()+"/system/dept?operation=list");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
