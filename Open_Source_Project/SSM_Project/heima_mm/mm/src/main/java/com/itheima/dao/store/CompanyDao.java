package com.itheima.dao.store;

import com.itheima.domain.store.Company;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-25 19:38
 */
public interface CompanyDao {
    int save(Company company);

    int delete(Company company);

    int update(Company company);

    Company findById(String id);

    List<Company> findAll();

}
