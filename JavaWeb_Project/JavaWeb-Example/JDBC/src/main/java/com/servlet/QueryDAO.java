package com.servlet;

import com.pojo.Person;
import com.utils.Dbutils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanMapHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: InsertDAO
 * @Desc:  从数据库中查询数据
 * @package com.servlet
 * @project JavaWeb-Example
 * @date 2020/6/27 21:16
 */
public class QueryDAO extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        try {
            Map map = query();

            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello MySQL</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>访问到了insertDAO.java</h1>");
            out.println("<p>map:" + map.toString() + "</p>");
            out.println("</body>");
            out.println("</html>");
            System.out.println("调用了insertDAO.java");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("doGet出现错误");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * @Desc 执行查询操作
     * @return Map 查询结果
     * @throws Exception
     */
    Map query() throws Exception {

        Connection conn = Dbutils.getConnection();

        if (conn != null) {
            String sql = "select * from `person`";
            BeanMapHandler<Object, Person> hdr = new BeanMapHandler<>(Person.class);
            QueryRunner qr = new QueryRunner();
            Map<Object, Person> map = qr.query(conn, sql, hdr);

            Dbutils.closeResource(conn);
            return map;
        }else{
            System.out.println("未获取到数据库连接");
            return null;
        }
    }
}
