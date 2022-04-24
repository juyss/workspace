package com.github.tangyi.exam.service;

import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.model.ExamStudyTask;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ExamStudyTaskService {
    ExamStudyTask get(Long id);

    PageResult list(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq);

    int save(ExamStudyTask examStudyTask);

    int del(Long id);

    int check(Long id, Integer status, String field);

    int joinCourse(Long id, String courseIds);

    int joinUser(Long id, String userIds);

    void delCourseRelation(Long courseId, Long taskId);

    void delUserRelation(Long userId, Long taskId);

    int cancelJoinCourse(Long id, String courseIds);

    int cancelJoinUser(Long id, String userIds);

    PageResult myTaskList(Integer pageNum, Integer pageSize, String sort, String order, String type);

    PageResult myTaskCourses(Integer pageNum, Integer pageSize, String sort, String order, Long taskId);

    Map<String, Object> myStatistics();

    void export(Long id, HttpServletResponse response);

    List<Map<String, Object>> getCourseList(Long id, Boolean join, BaseReq baseReq);

    PageResult getUserList(Integer pageNum, Integer pageSize, String sort, String order, Long id, Boolean join, BaseReq baseReq);

    Object myTaskWithCourseList(Integer pageNum, Integer pageSize, String sort, String order, String major);

    /**
     * 获取公开学习任务
     * @return
     */
    Object publicTaskList(Integer pageNum, Integer pageSize, String sort, String order, String major);

    PageResult studyRecordList(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq);

    Map<String, Object> myCourse(Long taskId, Long courseId);

    PageResult statistics(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq);

    PageResult userLoginStatistics(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq);
    PageResult accessTimeStatistics(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq);
    PageResult accessNumStatistics(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq);
}
