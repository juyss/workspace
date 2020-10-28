package com.itheima.dao.store;

import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Course;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-28 18:20
 */
public interface CatalogDao {
    int save(Catalog catalog);

    int delete(Catalog catalog);

    int update(Catalog catalog);

    Catalog findById(String id);

    List<Catalog> findAll();
}
