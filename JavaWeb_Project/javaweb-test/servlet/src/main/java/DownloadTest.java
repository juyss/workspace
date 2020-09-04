import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DownloadTest
 * @Desc:  通过Classloader获取文件输入流,失败,项目启动后找不到文件
 * @package PACKAGE_NAME
 * @project javaweb-03
 * @date 2020/6/16 16:43
 */
public class DownloadTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("avatar.jpg");

        FileOutputStream os = new FileOutputStream("avatar.jpg");

        resp.setHeader("Context-disposition", "attachment;filename=avatar.jpg");

        int len;
        byte[] buffer = new byte[1024];
        while((len=is.read(buffer))!=-1){
            os.write(buffer, 0, len);
        }

        os.close();
        is.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
