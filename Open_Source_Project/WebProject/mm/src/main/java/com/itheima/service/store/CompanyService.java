package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-26 8:46
 */
public interface CompanyService {

    /**
     * 添加
     * @param company
     */
    void save(Company company);

    /**
     * 删除
     * @param company
     */
    void delete(Company company);

    /**
     * 修改
     * @param company
     */
    void update(Company company);

    /**
     * 查询单个
     * @param id    查询的条件(id)
     * @return      查询的结果，单个对象
     */
    Company findById(String id);

    /**
     * 查询全部
     * @return  全部数据的列表对象
     */
    List<Company> findAll();

    /**
     * 分页查询数据
     * @param page  页码
     * @param size  每页显示条数
     * @return
     */
    PageInfo findAll(int page,int size);

}
