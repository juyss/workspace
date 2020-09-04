package com.itheima.dao.store;

import com.itheima.domain.store.Question;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-28 17:16
 */
public interface QuestionDao {
    int save(Question question);

    int delete(Question question);

    int update(Question question);

    Question findById(String id);

    List<Question> findAll();
}
