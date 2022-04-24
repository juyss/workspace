package com.juysss.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: FileUpload
 * @Desc:  文件上传练习
 * @package com.juysss.servlet
 * @project File_and_Mail
 * @date 2020/7/21 16:25
 */
public class FileUpload extends HttpServlet {
    /**
     * Called by the server (via the <code>service</code> method) to
     * allow a servlet to handle a GET request.
     *
     * <p>Overriding this method to support a GET request also
     * automatically supports an HTTP HEAD request. A HEAD
     * request is a GET request that returns no body in the
     * response, only the request header fields.
     *
     * <p>When overriding this method, read the request data,
     * write the response headers, get the response's writer or
     * output stream object, and finally, write the response data.
     * It's best to include content type and encoding. When using
     * a <code>PrintWriter</code> object to return the response,
     * set the content type before accessing the
     * <code>PrintWriter</code> object.
     *
     * <p>The servlet container must write the headers before
     * committing the response, because in HTTP the headers must be sent
     * before the response body.
     *
     * <p>Where possible, set the Content-Length header (with the
     * {@link ServletResponse#setContentLength} method),
     * to allow the servlet container to use a persistent connection
     * to return its response to the client, improving performance.
     * The content length is automatically set if the entire response fits
     * inside the response buffer.
     *
     * <p>When using HTTP 1.1 chunked encoding (which means that the response
     * has a Transfer-Encoding header), do not set the Content-Length header.
     *
     * <p>The GET method should be safe, that is, without
     * any side effects for which users are held responsible.
     * For example, most form queries have no side effects.
     * If a client request is intended to change stored data,
     * the request should use some other HTTP method.
     *
     * <p>The GET method should also be idempotent, meaning
     * that it can be safely repeated. Sometimes making a
     * method safe also makes it idempotent. For example,
     * repeating queries is both safe and idempotent, but
     * buying a product online or modifying data is neither
     * safe nor idempotent.
     *
     * <p>If the request is incorrectly formatted, <code>doGet</code>
     * returns an HTTP "Bad Request" message.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles the GET request
     * @throws ServletException if the request for the GET
     *                          could not be handled
     * @see ServletResponse#setContentType
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入了Servlet:FileUpload.class");
        String msg;
        //判断表单是否带有文件
        if (!ServletFileUpload.isMultipartContent(req)){
            msg="表单不含文件";
            req.setAttribute("msg",msg);
            req.getRequestDispatcher("info.jsp").forward(req,resp);
            return;
        }

        //定义文件上传后保存的根路径
        String rootPath = this.getServletContext().getRealPath("\\WEB-INF\\upload");
        File rootFile = new File(rootPath);
        if (!rootFile.exists()){
            rootFile.mkdir();
        }

        //定义文件上传后的缓存的根路径
        String tempPath = this.getServletContext().getRealPath("\\WEB-INF\\temp");
        File tempFile = new File(tempPath);
        if (!tempFile.exists()){
            tempFile.mkdir();
        }

        //创建DiskFileItemFactory对象,处理上传路径和限制文件大小
        DiskFileItemFactory FileItemFactory = new DiskFileItemFactory(1024 * 1024, tempFile);

        //创建ServletFileUpload对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(FileItemFactory);

        //获取FileItem对象
        List<FileItem> fileItems = null;
        try {
            fileItems = servletFileUpload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        if (fileItems==null){
            System.out.println("fileItems空指针");
            msg="fileItems空指针";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("info.jsp").forward(req,resp);
            return;
        }
        //遍历FileItem集合
        Iterator<FileItem> iterator = fileItems.iterator();
        while (iterator.hasNext()){
            FileItem fileItem = iterator.next();
            if (fileItem.isFormField()){ //普通文本表单,跳出循环
                continue;
            }else {
                String name = fileItem.getName(); //获取文件名
                System.out.println(name);
                if (name == null|| name.trim().equals("") ){ //判断文件名是否合法
                    continue;
                }

                //获取文件名和后缀
                //文件名
                String fileName;
                if (name.contains("/")){
                    fileName=name.substring(name.lastIndexOf("/"+1));
                }else {
                    fileName=name;
                }
                //文件后缀
                String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
                //获取随机UUID
                String uuid = UUID.randomUUID().toString();

                //使用uuid创建唯一的文件夹作为文件存放路径
                String savePath = rootPath+"\\"+uuid;
                File savePathFile = new File(savePath);
                if (!savePathFile.exists()){
                    savePathFile.mkdir();
                }

                //读取并储存到本地
                InputStream is = fileItem.getInputStream();

                FileOutputStream fos = new FileOutputStream(savePath + "/" + fileName);

                int len;
                byte[] bytes = new byte[1024*1024];
                while((len=is.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                }

                fos.close();
                is.close();
                //删除临时文件
                fileItem.delete();
                msg="上传成功";
                req.setAttribute("msg",msg);
                req.getRequestDispatcher("info.jsp").forward(req,resp);
            }

        }


    }

    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a POST request.
     * <p>
     * The HTTP POST method allows the client to send
     * data of unlimited length to the Web server a single time
     * and is useful when posting information such as
     * credit card numbers.
     *
     * <p>When overriding this method, read the request data,
     * write the response headers, get the response's writer or output
     * stream object, and finally, write the response data. It's best
     * to include content type and encoding. When using a
     * <code>PrintWriter</code> object to return the response, set the
     * content type before accessing the <code>PrintWriter</code> object.
     *
     * <p>The servlet container must write the headers before committing the
     * response, because in HTTP the headers must be sent before the
     * response body.
     *
     * <p>Where possible, set the Content-Length header (with the
     * {@link ServletResponse#setContentLength} method),
     * to allow the servlet container to use a persistent connection
     * to return its response to the client, improving performance.
     * The content length is automatically set if the entire response fits
     * inside the response buffer.
     *
     * <p>When using HTTP 1.1 chunked encoding (which means that the response
     * has a Transfer-Encoding header), do not set the Content-Length header.
     *
     * <p>This method does not need to be either safe or idempotent.
     * Operations requested through POST can have side effects for
     * which the user can be held accountable, for example,
     * updating stored data or buying items online.
     *
     * <p>If the HTTP POST request is incorrectly formatted,
     * <code>doPost</code> returns an HTTP "Bad Request" message.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles the request
     * @throws ServletException if the request for the POST
     *                          could not be handled
     * @see ServletOutputStream
     * @see ServletResponse#setContentType
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
