package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Question;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author zxq
 * @create 2020-08-28 17:19
 */
public interface QuestionService {
    /**
     *
     * @param question  要保存的题目对象
     * @param flag  是否有上传文件的操作
     * @return  保存的图片名称
     */
    String save(Question question,boolean flag);

    /**
     * 删除
     * @param question
     */
    void delete(Question question);

    /**
     * 修改
     * @param question
     * @param flag  是否有上传文件的操作
     */
    void update(Question question,boolean flag);

    /**
     * 查询单个
     * @param id    查询的条件(id)
     * @return      查询的结果，单个对象
     */
    Question findById(String id);

    /**
     * 查询全部
     * @return  全部数据的列表对象
     */
    List<Question> findAll();

    /**
     * 分页查询数据
     * @param page  页码
     * @param size  每页显示条数
     * @return
     */
    PageInfo findAll(int page, int size);

    /**
     * 获取包含了数据的流对象
     * @return  包含了报表数据的流对象
     * @throws IOException
     */
    ByteArrayOutputStream getReport() throws IOException;
}
