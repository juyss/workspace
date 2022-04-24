package com.shme.session;

import com.shme.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SessionTest
 * @Desc:
 * @package com.shme
 * @project javaweb-session
 * @date 2020/6/17 21:56
 */
public class SessionPut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();

        Person person = new Person("Shmebluk", 15);
        session.setAttribute("name", person);

        String id = session.getId();

        if (session.isNew()){
            resp.getWriter().write("Session创建成功:"+id);
        }else{
            resp.getWriter().write("Session已存在:"+id);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
