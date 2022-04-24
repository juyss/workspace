package com.icepoint.framework.web.security.controller;


import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ck
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService service;

    /**
     * 用户管理
     * @return 分页后的数据
     */
    @GetMapping("pageList")
    public PageResponse<User> pageList(User user, Pageable pageable){
        return ResponseUtils.page(service.pageList(user,pageable));
    }

    /**
     * 新增用户
     */
    @PostMapping("addUser")
    public Response<User> addUser(@RequestBody User user){
        return ResponseUtils.one(service.addUser(user));
    }

}
