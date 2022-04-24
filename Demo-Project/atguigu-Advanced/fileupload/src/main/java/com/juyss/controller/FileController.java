package com.juyss.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: FileController
 * @Desc: 文件上传和下载
 * @package com.juyss.controller
 * @project atguigu-Advanced
 * @date 2020/12/8 19:37
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 文件上传控制器
     * @param file 上传的文件
     * @return 上传信息
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request){

        //判断传入文件是否为空
        if (file == null || file.isEmpty()) {
            return "未选择需上传的文件";
        }

        //定义文件保存位置(绝对路径)
        //String realPath = request.getSession().getServletContext().getRealPath("/upload");
        String filePath = new File("D:\\WorkSpace\\Demo-Project\\atguigu-Advanced\\fileupload\\src\\main\\resources\\upload").getAbsolutePath();
        //判断文件路径是否存在,不存在就创建路径
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }

        //根据日期创建

        //创建文件对象,指定文件保存路径和文件名
        fileUpload = new File(filePath, Objects.requireNonNull(file.getOriginalFilename()));

        //判断是否存在同名文件
        if (fileUpload.exists()) {
            return "上传的文件已存在";
        }

        //保存文件
        try {
            file.transferTo(fileUpload);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传到服务器失败";
        }

        //返回信息
        return file.getOriginalFilename()+"文件上传成功";
    }

    /**
     * 文件下载控制器
     * @param fileName 要下载的文件名
     * @param request 请求
     * @param response 响应
     * @return 返回响应信息
     */
    @RequestMapping("/download/{fileName}")
    public String download(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response){

        //获取下载文件路径
        //String realPath = request.getServletContext().getRealPath("/download"); //相对路径
        String realPath = "D:\\WorkSpace\\Demo-Project\\atguigu-Advanced\\fileupload\\src\\main\\resources\\download"; //绝对路径

        //创建文件抽象类
        File file = new File(realPath,fileName);

        //判断文件是否存在
        if(!file.exists()){
            return "文件不存在";
        }

        //从服务器通过文件输入流读入文件,然后通过文件输出流由Response写出给浏览器
        FileInputStream is = null;
        ServletOutputStream os = null;
        try {
            is = new FileInputStream(file);

            //设置响应头信息
            response.setHeader("content-disposition", "attachment:fileName="+ URLEncoder.encode(fileName, "UTF-8"));
            os = response.getOutputStream();

            //IO工具类复制操作
            IOUtils.copy(is, os);

        } catch (IOException e) {
            e.printStackTrace();
            return "文件下载错误";
        } finally {
            //关闭资源
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
        return "下载成功!!!";
    }

}
