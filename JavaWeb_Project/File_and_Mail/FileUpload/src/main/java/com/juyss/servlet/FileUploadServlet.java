package com.juyss.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
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
 * @ClassName: ${NAME}
 * @Desc: 网页文件上传功能
 * @package ${PACKAGE_NAME}
 * @project File_and_Mail
 * @date 2020/7/17 17:35
 */
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入了FileUploadServlet.class");
        String msg;

        //判断表单是否为带有文件的表单
        if (!ServletFileUpload.isMultipartContent(request)) {
            msg="表单不含文件";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("info.jsp").forward(request,response);
            return; //终止方法运行
        }

        //定义文件上传后保存的根路径
        String rootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            rootFile.mkdir();
        }

        //定义文件缓存路径
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }

        //创建DiskFileItemFactory对象,处理上传路径和限制文件大小
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(1024 * 1024, tempFile);

        //创建ServletFileUpload对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

        //获取FileItem对象
        List<FileItem> fileItems = null;
        try {
            fileItems = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        if (fileItems==null){
            System.out.println("fileItem空指针");
            msg="fileItem空指针";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("info.jsp").forward(request,response);
            return;
        }
        Iterator<FileItem> iterator = fileItems.iterator();

        //遍历fileItems集合
        while (iterator.hasNext()) {
            FileItem fileItem = iterator.next();
            if (fileItem.isFormField()) {//是普通文本表单
                continue; //跳出循环
            } else {//是文件表单
                //获取文件字段名,标签中name属性的值
                String fieldName = fileItem.getFieldName();
                //获取文件上传字段中的文件名,一般来说为为文件名(不包含路径信息),在opera浏览器中可能会包含文件路径信息
                String name = fileItem.getName();
                System.out.println("文件字段名:"+fieldName);
                System.out.println("文件名:"+name);

                //判断文件名是否合法
                if (name.trim().equals("") || name == null) {
                    continue;
                }

                //获取文件名和文件后缀
                String fileName; //文件名
                if (name.contains("/")){ //判断name是否包含路径信息
                    fileName = name.substring(name.lastIndexOf("/")+1);
                }else{
                    fileName = name;
                }

                String fileExtension = fileName.substring(fileName.indexOf(".") + 1);//获取文件后缀
                System.out.println("文件名:" + fileName);
                System.out.println("文件类型:" + fileExtension);
                //获取随机UUID
                String uuid = UUID.randomUUID().toString();

                //使用uuid创建唯一的文件夹作为文件存放路径
                String savePath = rootPath + "/" + uuid; //文件存放路径
                File savePathFile = new File(savePath);
                if (!savePathFile.exists()) { //路径不存在就创建
                    savePathFile.mkdir();
                }

                //获取输入流读取上传的文件
                InputStream is = fileItem.getInputStream();

                //用输出流保存到本地
                FileOutputStream fos = new FileOutputStream(savePath + "/" + fileName);

                int len;
                byte[] bytes = new byte[1024 * 1024];
                while((len = is.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                }

                fos.close();
                is.close();
                //删除临时文件
                fileItem.delete();
                msg="上传成功";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("info.jsp").forward(request,response);
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
