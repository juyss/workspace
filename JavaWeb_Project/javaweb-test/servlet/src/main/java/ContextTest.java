import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ContextTest
 * @Desc:
 * @package PACKAGE_NAME
 * @project javaweb-03
 * @date 2020/6/15 13:34
 */
public class ContextTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = this.getServletContext();

        String url = "jdbc:mysql://localhost:8080";
        context.setAttribute("url",url);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
