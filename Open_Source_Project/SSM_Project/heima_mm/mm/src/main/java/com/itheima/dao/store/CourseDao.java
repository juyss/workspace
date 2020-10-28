package com.itheima.dao.store;

import com.itheima.domain.store.Course;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-28 17:35
 */
public interface CourseDao {
    int save(Course course);

    int delete(Course course);

    int update(Course course);

    Course findById(String id);

    List<Course> findAll();
}
