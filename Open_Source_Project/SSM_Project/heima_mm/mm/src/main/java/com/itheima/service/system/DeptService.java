package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-26 8:53
 */
public interface DeptService {

    void save(Dept dept);

    void delete(Dept dept);

    void update(Dept dept);

    Dept findById(String id);

    List<Dept> findAll();

    PageInfo findAll(int page,int size);
}
