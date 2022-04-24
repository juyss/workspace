package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.example.ExamStudyTaskUserInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExamStudyTaskMapper {


    List<Map<String, Object>> getMyTaskCourses(@Param("taskId") Long taskId, @Param("userId") Long userId, @Param("sort") String sort, @Param("order") String order);

    Map<String, Object> myStatistics(@Param("userId") Long userId);

    List<Map<String, Object>> getMyTasksByExample(ExamStudyTaskUserInfoExample examStudyTaskUserInfoExample);

    List<Map<String, Object>> getUserList(BaseReq baseReq, @Param("join") Boolean join, @Param("id") Long id, @Param("sort") String sort, @Param("order") String order);

    List<Map<String, Object>> getCourseList(@Param("id") Long id, @Param("join") Boolean join, BaseReq baseReq);

    Integer sumStudyTime(@Param("id") Long id);

    List<Map<String, Object>> studyRecordList(BaseReq baseReq, @Param("sort") String sort, @Param("order") String order);

    Map<String, Object> myCourse(@Param("taskId") Long taskId, @Param("courseId") Long courseId, @Param("userId") Long userId);

    List<Map<String, Object>> statistics(BaseReq baseReq, @Param("sort") String sort, @Param("order") String order);

    List<Map<String, Object>> userLoginStatistics(BaseReq baseReq, @Param("sort") String sort, @Param("order") String order);

    List<Map<String, Object>> accessTimeStatistics(BaseReq baseReq, @Param("sort") String sort, @Param("order") String order);

    List<Map<String, Object>> accessNumStatistics(BaseReq baseReq, @Param("sort") String sort, @Param("order") String order);

    List<Map<String, Object>> courseListForFe(@Param("sort") String sort, @Param("order") String order);

    List<String> getMyTaskMajorList(@Param("userId") Long userId);

    List<Map<String, Object>> getTaskList(@Param("sort") String sort, @Param("order") String order, @Param("userId") Long userId, @Param("major") String major);

    /**
     * 根据专业获取公开的学习任务
     * @return 查询到的学习任务集合
     */
    List<Map<String, Object>> getPublicTaskList(@Param("major") String major,@Param("sort") String sort, @Param("order") String order);

    /**
     * 获取所有的专业分类
     * @return
     */
    List<String> getMajorList();

    /**
     * 根据专业和任务id获取课程数据
     * @param major 专业
     * @param taskId 任务id
     * @return
     */
    List<Map<String, Object>> getCourseWithMajorAndTaskId(@Param("major") String major,@Param("taskId") Long taskId);

}
