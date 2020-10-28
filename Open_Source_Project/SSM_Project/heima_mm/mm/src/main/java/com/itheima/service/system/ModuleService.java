package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 * @author zxq
 * @create 2020-08-29 17:20
 */
public interface ModuleService {
    void save(Module module);

    void delete(Module module);

    void update(Module module);

    Module findById(String id);

    List<Module> findAll();

    PageInfo findAll(int page, int size);

    /**
     * 根据角色id获取对应的所有模块关联数据
     * @param roleId    角色id
     */
    List<Map> findAuthorDataByRoleId(String roleId);
}
