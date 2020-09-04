package com.itheima.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.QuestionItem;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zxq
 * @create 2020-08-30 11:25
 */
@WebServlet("/store/questionItem")
public class QuestionItemServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取operation的类型
        String operation = request.getParameter("operation");

        //功能分发
        if ("list".equals(operation)) {
            this.list(request, response);
        } else if ("save".equals(operation)) {
            this.save(request, response);
        } else if ("toEdit".equals(operation)) {
            this.toEdit(request, response);
        } else if ("edit".equals(operation)) {
            this.edit(request, response);
        } else if ("delete".equals(operation)) {
            this.delete(request, response);
        }else if ("saveOrUpdate".equals(operation)) {
            this.saveOrUpdate(request, response);
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String questionId = request.getParameter("questionId");

        //进入list页时添加对应的问题id，为添加操作使用
        request.setAttribute("questionId", questionId);
        PageInfo all = questionItemService.findAll(questionId, 1, 100);

        //将数据保存到指定的位置
        request.setAttribute("page", all);

        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(request, response);
    }


    private void saveOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //1.获取数据，封装为对象
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class);

        //判断页面是否传递ID
        if (StringUtils.isNotBlank(questionItem.getId())) {

            //2.1 ID不空则为修改：调用业务层接口update
            questionItemService.update(questionItem);

        } else {
            //2.2 ID为空则为保存：调用业务层接口save
            questionItemService.save(questionItem);
        }
        //3.跳转到list页面
        list(request, response);
    }


    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        //查询要修改的数据findById
        QuestionItem questionItem = questionItemService.findById(id);

        //将数据加载到指定区域，供页面获取
        request.setAttribute("questionItem", questionItem);

        //跳转页面
        list(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //1.获取数据，封装为对象
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class);

        //2.调用业务层接口save
        questionItemService.save(questionItem);

        //3.跳转到list页面
        list(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //1.获取数据，将数据封装成对象
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class);

        //2.调用业务层接口
        questionItemService.update(questionItem);

        //3.跳转到list页面
        list(request, response);

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //1.获取数据，将数据封装成对象
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class);

        //2.调用业务层接口
        questionItemService.delete(questionItem);

        //3.跳到list页面
        list(request, response);
    }

    private void list2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String questionId = request.getParameter("questionId");

        //进入list页时添加对应的问题id，为添加操作使用
        request.setAttribute("questionId", questionId);
        PageInfo all = questionItemService.findAll(questionId, 1, 100);

        //将数据保存到指定的位置
        request.setAttribute("page", all);

        if (request.getAttribute("operation") == null) {
            //保存一个操作的类型传递给前端
            request.setAttribute("operation", "save");
        }

        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(request, response);
    }

    private void toEdit2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        //查询要修改的数据findById
        QuestionItem questionItem = questionItemService.findById(id);
        //将数据加载到指定区域，供页面获取
        request.setAttribute("questionItem", questionItem);

        //保存一个操作的类型传递给前端
        request.setAttribute("operation", "edit");

        //跳转页面
        list(request, response);
    }
}
