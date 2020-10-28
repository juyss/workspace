package com.itheima.web.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Role;
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
import java.util.Map;
import java.util.UUID;

/**
 * @author zxq
 * @create 2020-08-29 15:45
 */
@WebServlet("/system/role")
public class RoleServlet extends BaseServlet {

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
        } else if ("author".equals(operation)) {
            this.author(request, response);
        }else if ("updateRoleModule".equals(operation)) {
            this.updateRoleModule(request, response);
        }


    }



    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int size = 5;
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (StringUtils.isNotBlank(request.getParameter("size"))) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = roleService.findAll(page, size);

        request.setAttribute("page", all);

        //跳转页面，转发到list页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(request, response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //转发到add页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //收集数据
        Role role = BeanUtil.fillBean(request, Role.class);

        //id非空，需要加上
        String id = UUID.randomUUID().toString();
        role.setId(id);
        //createTime也需要加上
        role.setCreateTime(new Date());
        //保存到数据库
        roleService.save(role);

        //跳转到list页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");

    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要修改的id
        String id = request.getParameter("id");

        //将此人信息从数据库读出来，发送给edit页面
        Role role = roleService.findById(id);
        request.setAttribute("role", role);

        //跳转到修改update页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(request, response);


    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取修改信息，封装到javaBean
        Role role = BeanUtil.fillBean(request, Role.class);

        //将此人信息保存到数据库
        roleService.update(role);

        //跳转页面到list
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据，封装成对象
        Role role = BeanUtil.fillBean(request, Role.class);

        //业务层处理业务
        roleService.delete(role);

        //跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void author(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取要授权的角色id
        String roleId = request.getParameter("id");

        //使用id查询对应的数据（角色id对应的模块信息）
        Role role = roleService.findById(roleId);
        request.setAttribute("role",role);
        //根据当前的角色id获取所有的模块数据，并加载关系数据
        List<Map> map = moduleService.findAuthorDataByRoleId(roleId);
        //map转成json
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(map);

        request.setAttribute("roleModuleJson",json);

        //TODO 数据未查询
        //跳转到树页面中
        //跳转到修改update页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(request, response);

    }

    private void updateRoleModule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("roleId");
        String moduleIds = request.getParameter("moduleIds");

        roleService.updateRoleModule(roleId,moduleIds);

        //跳转回页面list
        response.sendRedirect(request.getContextPath()+"/system/role?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
