package com.icepoint.framework.web.security.service;

import com.icepoint.framework.web.security.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Administrator
 */
public interface UserService {
    /**
     * 用户管理
     * @param pageable 分页
     */
    Page<User> pageList(@RequestParam User user,@RequestParam Pageable pageable);

    /**
     * 新增用户
     * @param user
     * @return 用户
     */
    User addUser(User user);
}
