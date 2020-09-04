package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.QuestionItem;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-30 11:21
 */
public interface QuestionItemService {
    /**
     * 添加
     *
     * @param questionItem
     */
    void save(QuestionItem questionItem);

    /**
     * 删除
     *
     * @param questionItem
     */
    void delete(QuestionItem questionItem);

    /**
     * 修改
     *
     * @param questionItem
     */
    void update(QuestionItem questionItem);

    /**
     * 查询单个
     *
     * @param id 查询的条件(id)
     * @return 查询的结果，单个对象
     */
    QuestionItem findById(String id);

    /**
     * 分页查询数据
     *
     * @param questionId 题目对应的id
     * @param page 页码
     * @param size 每页显示条数
     * @return
     */
    PageInfo findAll(String questionId, int page, int size);
}
