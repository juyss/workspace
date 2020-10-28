package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.User;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-26 8:56
 */
public interface UserService {
    void save(User user);

    void delete(User user);

    void update(User user);

    User findById(String id);

    List<User> findAll();

    PageInfo findAll(int page,int size);

    void updateRole(String userId, String[] roleIds);

    User login(String email, String password);

    List<Module> findModuleById(String id);
}
