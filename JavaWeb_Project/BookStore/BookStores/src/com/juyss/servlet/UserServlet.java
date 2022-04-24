package com.juyss.servlet;

import com.juyss.pojo.User;
import com.juyss.service.UserService;
import com.juyss.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServlet
 * @Desc: 处理用户请求
 * @package com.juyss.servlet
 * @project BookStore
 * @date 2020/8/8 9:31
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 处理用户登录请求
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        BeanUtils.populate(user, req.getParameterMap());

        System.out.println("前端传入用户名:" + user.getUsername() + ",密码:" + user.getPassword());

        User userQueried = userService.login(user.getUsername());
        System.out.println("数据库查询到的数据:" + user);
        if (userQueried != null) {
            if (userQueried.getPassword().equals(user.getPassword())) {
                System.out.println("登录成功");
                req.getSession().setAttribute("user_session", user);
                resp.sendRedirect("pages/user/login_success.jsp");
            } else {
                System.out.println("登录失败");
                req.setAttribute("errorMsg", "密码错误");
                req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
            }
        } else {
            System.out.println("登录失败");
            req.setAttribute("errorMsg", "用户名不存在");
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * 处理用户注册请求
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void signin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, InvocationTargetException, IllegalAccessException {
        //获取请求参数和Session中的验证码
        String kaptcha = req.getParameter("kaptcha");
        String sessionKey = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        System.out.println("请求参数中的验证码-->" + kaptcha);
        System.out.println("Session中的验证码-->" + sessionKey);

        //移除Session中的验证码
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");

        User user = new User();

        BeanUtils.populate(user, req.getParameterMap());

        System.out.println("前端传入数据:\n" + user.getUsername() + "\n" + user.getPassword() + "\n" + user.getEmail());

        //判断Session中验证码不为空且与请求参数的验证码相同,判断为第一次请求,允许注册
        if (sessionKey != null && sessionKey.equalsIgnoreCase(kaptcha)) {
            //查询是否存在此用户名
            User userQueried = userService.login(user.getUsername());

            //如果用户名已存在
            if (userQueried != null) {
                req.setAttribute("errorMsg", "用户名已存在!");
                req.getRequestDispatcher("pages/user/signin.jsp").forward(req, resp);
                return;
            }

            //用户名不存在,写入数据库
            Boolean signin = userService.signin(user);
            if (signin) {
                req.getSession().setAttribute("user_session", user);
                resp.sendRedirect("pages/user/signin_success.jsp");
            } else {
                req.setAttribute("errorMsg", "注册失败");
                req.getRequestDispatcher("pages/user/signin.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMsg", "验证码错误");
            req.getRequestDispatcher("pages/user/signin.jsp").forward(req, resp);
        }
    }

    /**
     * 用户注销请求
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //销毁Session
//        req.getSession().invalidate();
        //删除session
        req.getSession().removeAttribute("user_session");

        //重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

}
