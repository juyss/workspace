package com.itheima.dao.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 * @author zxq
 * @create 2020-08-29 17:18
 */
public interface ModuleDao {
    void save(Module module);

    void delete(Module module);

    void update(Module module);

    Module findById(String id);

    List<Module> findAll();

    PageInfo findAll(int page, int size);

    List<Map> findAuthorDataByRoleId(String roleId);

    List<Module> findModuleByUserId(String id);
}
