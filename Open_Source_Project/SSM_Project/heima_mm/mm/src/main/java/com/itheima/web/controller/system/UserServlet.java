package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;
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
 * @create 2020-08-26 8:57
 */
@WebServlet("/system/user")
public class UserServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求方法的类型
        String operation = request.getParameter("operation");

        //请求分发
        if ("list".equals(operation)) {
            this.list(request, response);
        }else if ("toAdd".equals(operation)) {
            this.toAdd(request, response);
        }else if ("save".equals(operation)) {
            this.save(request, response);
        }else if ("toEdit".equals(operation)) {
            this.toEdit(request, response);
        }else if ("edit".equals(operation)) {
            this.edit(request, response);
        }else if ("delete".equals(operation)) {
            this.delete(request, response);
        }else if ("userRoleList".equals(operation)) {
            this.userRoleList(request, response);
        }else if ("updateRole".equals(operation)) {
            this.updateRole(request, response);
        }else if ("login".equals(operation)) {
            this.login(request, response);
        }else if ("home".equals(operation)) {
            this.home(request, response);
        }else if ("logout".equals(operation)) {
            this.logout(request, response);
        }
    }



    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //默认设置
        int page=1;
        int size=5;
        //接收来自客户端的设置
        if(StringUtils.isNotBlank(request.getParameter("paze"))){
            page = Integer.parseInt(request.getParameter("paze"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            size = Integer.parseInt(request.getParameter("size"));
        }

        //调用业务层获取数据
        PageInfo all = userService.findAll(page, size);
        //将数据传给页面
        request.setAttribute("page",all);
        //跳转页面list(转发)
        request.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(request,response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询部门信息,放到指定区域，供页面使用
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);

        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将数据获取到，封装成User对象
        User user = BeanUtil.fillBean(request,User.class,"yyyy-MM-dd");
        //调用业务层接口save
        userService.save(user);
        //跳转回到页面list(重定向)
        response.sendRedirect(request.getContextPath()+"/system/user?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询要修改的数据findById
        String id=request.getParameter("id");
        User user = userService.findById(id);
        //将数据加载到指定区域，供页面获取
        request.setAttribute("user",user);
        //将所有部门信息加载到指定区域deptList
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        //跳转页面update(转发)
        request.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(request,response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据，封装成User对象
        User user = BeanUtil.fillBean(request,User.class,"yyyy-MM-dd");
        //调用业务层接口update
        userService.update(user);
        //跳转回到页面list(重定向)
        response.sendRedirect(request.getContextPath()+"/system/user?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据，封装成User对象
        User user = BeanUtil.fillBean(request,User.class);
        //调用业务层接口delete
        userService.delete(user);
        //跳转回页面list(重定向)
        response.sendRedirect(request.getContextPath()+"/system/user?operation=list");
    }

    private void userRoleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("id");
        User user = userService.findById(userId);

        //将数据加载到指定区域，供页面获取
        request.setAttribute("user",user);
        //获取所有的角色列表
        List<Role> all = roleService.findAllRoleByUserId(userId);
        request.setAttribute("roleList",all);

        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/role.jsp").forward(request,response);

    }

    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //从前台收集数据，用户id和该用户的新增的角色
        String userId = request.getParameter("userId");
        String[] roleIds = request.getParameterValues("roleIds");

        //调用业务层，将角色绑定到用户
        userService.updateRole(userId,roleIds);

        //跳转页面到list
        response.sendRedirect(request.getContextPath()+"/system/user?operation=list");

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(request,response);

        if (true){
            return;
        }
        //从前端收集数据
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //调用业务层，处理业务
        User user = userService.login(email,password);

        //根据结果判断是否登录成功
        if(user != null){
            request.getSession().setAttribute("loginUser",user);

            //如果登录成功，加载该用户对应的所有的模块
            List<Module> moduleList = userService.findModuleById(user.getId());
            request.setAttribute("moduleList",moduleList);

            //将当前登录用户对应的可操作的所有url，拼接成一个大的字符串
            StringBuffer sbf =new StringBuffer();
            for (Module m : moduleList) {
                sbf.append(m.getCurl());
                sbf.append(",");
            }
            request.getSession().setAttribute("authorStr",sbf.toString());

            //登录成功，去往成功页面，跳转页面
            request.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(request,response);

        }else{
            //登录失败，跳回登录页面
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }


    }

    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //跳转到home页面
        request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request,response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("loginUser");

        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
