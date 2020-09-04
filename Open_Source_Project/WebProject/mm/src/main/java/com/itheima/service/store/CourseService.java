package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Course;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-28 17:36
 */
public interface CourseService {
    /**
     * 添加
     * @param course
     */
    void save(Course course);

    /**
     * 删除
     * @param course
     */
    void delete(Course course);

    /**
     * 修改
     * @param course
     */
    void update(Course course);

    /**
     * 查询单个
     * @param id    查询的条件(id)
     * @return      查询的结果，单个对象
     */
    Course findById(String id);

    /**
     * 查询全部
     * @return  全部数据的列表对象
     */
    List<Course> findAll();

    /**
     * 分页查询数据
     * @param page  页码
     * @param size  每页显示条数
     * @return
     */
    PageInfo findAll(int page, int size);
}
