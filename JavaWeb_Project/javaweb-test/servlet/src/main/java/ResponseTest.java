import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ResponseTest
 * @Desc:  定义文件的绝对路径,设置响应头,实现浏览器下载操作
 * @package PACKAGE_NAME
 * @project javaweb-03
 * @date 2020/6/16 10:40
 */
public class ResponseTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取文件真实路径
        //String path = this.getServletContext().getRealPath("WEB-INF\\classes\\avatar.jpg");
        String path = "C:\\Users\\Administrator\\IdeaProjects\\javaweb-test\\servlet\\target\\servlet\\WEB-INF\\classes\\avatar.png";

        //获取下载目标文件名
        String fileName = path.substring(path.lastIndexOf("\\") + 1);

        //设置响应头
        resp.setHeader("Content-Disposition", "attachment; filename="+fileName);

        //获取输入流
        FileInputStream is = new FileInputStream(path);

        //创建文件输出流
        ServletOutputStream os = resp.getOutputStream();

        //写出文件
        int len;
        byte[] buffer = new byte[1024];
        while((len=is.read(buffer))!=-1){
            os.write(buffer, 0, len);
        }

        //关闭资源
        os.close();
        is.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
