package com.itheima.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Company;
import com.itheima.domain.store.Question;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zxq
 * @create 2020-08-28 17:25
 */
@WebServlet("/store/question")
public class QuestionServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //获取operation的类型
        String operation = request.getParameter("operation");

        //功能分发
        if("list".equals(operation)){
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            try {
                this.save(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            try {
                this.edit(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }else if("toTestUpload".equals(operation)){
            this.toTestUpload(request,response);
        }else if("testUpload".equals(operation)){
            try {
                this.testUpload(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("downloadReport".equals(operation)){
            this.downloadReport(request,response);
        }
    }



    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //默认设置
        int page = 1;
        int size= 5;
        //接收来自客户端的设置
        if(StringUtils.isNoneBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNoneBlank(request.getParameter("size"))){
            size = Integer.parseInt(request.getParameter("size"));
        }

        //调用业务层处理业务
        PageInfo all = questionService.findAll(page,size);
        //将数据保存到指定的位置
        request.setAttribute("page",all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(request,response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //添加前把需要的数据发送给add页面
        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();

        request.setAttribute("companyList",companyList);
        request.setAttribute("catalogList",catalogList);


        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.确认该操作是否支持文件上传操作，enctype=multipart/form-data
        if(ServletFileUpload.isMultipartContent(request)){
            //2.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //3.创建ServletUpload核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            //4.解析request
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            //创建一个标记位，记录前端上传数据是否上传文件
            boolean flag = false;
            for (FileItem item : fileItems) {

                if(StringUtils.isNotBlank(item.getName())){
                    flag=true;
                    break;
                }
            }

            //5.将表单数据封装到javaBean中
            Question question = BeanUtil.fillBean(fileItems, Question.class);

            //6.调用业务层保存表单数据
            String picture = questionService.save(question,flag);

            //7.接收上传的文件
            for (FileItem item : fileItems) {
                //判断是不是文件
                if(!item.isFormField()){
                    //是文件，保存到服务器
                    item.write(new File(this.getServletContext().getRealPath("upload"),picture));
                }
            }

        }

        //8.跳转到list页面
        response.sendRedirect(request.getContextPath()+"/store/question?operation=list");

    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //查询要修改的数据findById
        String id = request.getParameter("id");
        Question question = questionService.findById(id);
        //将数据加载到指定区域，供页面获取
        request.setAttribute("question",question);

        //修改前把需要的数据发送给add页面
        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();

        request.setAttribute("companyList",companyList);
        request.setAttribute("catalogList",catalogList);

        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(request,response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.确认该操作是否支持文件上传操作，enctype=multipart/form-data
        if(ServletFileUpload.isMultipartContent(request)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //3.创建ServletUpload核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            //4.解析request
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            //创建一个标记位，记录前端上传数据是否上传文件
            boolean flag = false;
            for (FileItem item : fileItems) {

                if(StringUtils.isNotBlank(item.getName())){
                    flag=true;
                    break;
                }
            }

            //5.将表单数据封装到javaBean中
            Question question = BeanUtil.fillBean(fileItems, Question.class);

            //6.调用业务层保存表单数据
            questionService.update(question,flag);

            //7.接收上传的文件
            for (FileItem item : fileItems) {
                //判断是不是文件
                if (!item.isFormField()) {
                    //是文件，保存到服务器
                    item.write(new File(this.getServletContext().getRealPath("upload"), question.getId()));
                }
            }
        }

        //3.跳转到list页面
        response.sendRedirect(request.getContextPath()+"/store/question?operation=list");


    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //1.获取数据，将数据封装成对象
        Question question = BeanUtil.fillBean(request, Question.class);

        //2.调用业务层接口
        questionService.delete(question);

        //3.跳到list页面
        response.sendRedirect(request.getContextPath()+"/store/question?operation=list");

    }

    private void toTestUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //跳转到上传页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/testFileUpload.jsp").forward(request,response);
    }

    private void testUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.确认该操作是否支持文件上传操作，enctype=multipart/form-data
        if(ServletFileUpload.isMultipartContent(request)){
            //2.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //3.Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            //4.从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            for (FileItem item : fileItems) {
                //5.当前表单是否是文件表单
                if(!item.isFormField()){
                    item.write(new File(this.getServletContext().getRealPath("upload"),item.getName()));
                }

            }

        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    private void downloadReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //返回的数据类型为文件xlsx类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        String fileName = new String("题目数据.xlsx".getBytes(),"iso8859-1");
        response.addHeader("Content-Disposition","attachment;fileName="+fileName);

        //生成报告的文件，传递给前端的页面
        ByteArrayOutputStream os = questionService.getReport();
        //获取产生相应的流对象
        ServletOutputStream sos = response.getOutputStream();
        //将数据从原始的字节流对象中读取出来写入到servlet对应的输出流中
        os.writeTo(sos);
        //将输出流刷新
        sos.flush();
        os.close();
    }
    
}
