package com.github.tangyi.exam.service;

import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.model.ExamCourse;

public interface ExamCourseService {
    ExamCourse get(Long id);

    int save(ExamCourse course);

    int del(Long id);

    PageResult list(Integer pageNum, Integer pageSize, String sort, String order, BaseReq param);

    boolean check(Long id, Integer status, String field);

    PageResult courseListForFe(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq);
}
