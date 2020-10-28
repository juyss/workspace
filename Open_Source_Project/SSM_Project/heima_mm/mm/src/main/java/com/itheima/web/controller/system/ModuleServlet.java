package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Module;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zxq
 * @create 2020-08-29 17:22
 */
@WebServlet("/system/module")
public class ModuleServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求方法的类型
        String operation = request.getParameter("operation");

        //分发请求
        if ("list".equals(operation)) {
            this.list(request, response);
        } else if ("toAdd".equals(operation)) {
            this.toAdd(request, response);
        } else if ("save".equals(operation)) {
            this.save(request, response);
        } else if ("toEdit".equals(operation)) {
            this.toEdit(request, response);
        } else if ("edit".equals(operation)) {
            this.edit(request, response);
        } else if ("delete".equals(operation)) {
            this.delete(request, response);
        }


    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int size = 10;
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (StringUtils.isNotBlank(request.getParameter("size"))) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = moduleService.findAll(page, size);

        request.setAttribute("page", all);

        //跳转页面，转发到list页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/list.jsp").forward(request, response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加载所有的部门信息放入到add页面的moduleList中
        List<Module> all = moduleService.findAll();
        request.setAttribute("moduleList",all);

        //转发到add页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //收集数据
        Module module = BeanUtil.fillBean(request, Module.class);

        //id非空，需要加上
        String id = UUID.randomUUID().toString();
        module.setId(id);

        //保存到数据库
        moduleService.save(module);

        //跳转到list页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");

    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要修改的id
        String id = request.getParameter("id");

        //将此人信息从数据库读出来，发送给edit页面
        Module module = moduleService.findById(id);
        request.setAttribute("module", module);

        //跳转到修改update页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/update.jsp").forward(request, response);


    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取修改信息，封装到javaBean
        Module module = BeanUtil.fillBean(request, Module.class);

        //将此人信息保存到数据库
        moduleService.update(module);

        //跳转页面到list
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据，封装对象
        Module module = BeanUtil.fillBean(request, Module.class);

        //业务层处理业务
        moduleService.delete(module);

        //跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
