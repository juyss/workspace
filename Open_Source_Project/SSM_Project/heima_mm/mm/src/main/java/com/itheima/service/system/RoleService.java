package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Role;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-29 15:32
 */
public interface RoleService {
    void save(Role role);

    void delete(Role role);

    void update(Role role);

    Role findById(String id);

    List<Role> findAll();

    PageInfo findAll(int page,int size);

    void updateRoleModule(String roleId, String moduleIds);

    List<Role> findAllRoleByUserId(String userId);
}
