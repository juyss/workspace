package com.github.tangyi.exam.service;

import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.model.TrainCourse;

import javax.servlet.http.HttpServletResponse;

public interface TrainCourseService {

    int save(TrainCourse trainCourse);

    PageResult getList(BaseReq baseReq, Integer pageNum, Integer pageSize, String sort, String order);

    int del(Long id);

    PageResult getRelationList(BaseReq baseReq,Long id, Integer pageNum, Integer pageSize, String sort, String order);

    PageResult getApplyList(Integer pageNum, Integer pageSize, String sort, String order);

    PageResult getSignList(Integer pageNum, Integer pageSize, String sort, String order);

    int apply(Long trainCourseId);

    int sign(Long trainCourseId);

    void export(Long id, HttpServletResponse response);

    TrainCourse check(Long id, Integer status);
}