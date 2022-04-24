package com.icepoint.framework.web.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.security.dao.UserMapper;
import com.icepoint.framework.web.security.dao.UserRepository;
import com.icepoint.framework.web.security.entity.Organization;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.service.AuthService;
import com.icepoint.framework.web.security.service.DepartmentService;
import com.icepoint.framework.web.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 用户管理
 *
 * @author Administrator
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    private final DepartmentService departmentService;

    private final AuthService authService;

    @Override
    public Page<User> pageList(User entity, Pageable pageable) {
        Page<User> users = repository.findAll(Example.of(entity), pageable);
        for (User user : users) {
            //获取所有user的部门
            Organization organization = departmentService.findById(user.getDeptId());
            user.setDeptName(organization.getName());
        }
        return users;
    }

    @Override
    public User addUser(User user) {
        authService.register(user);
        return user;
    }


}
