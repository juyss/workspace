package com.juyss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juyss.entity.User;
import com.juyss.mapper.UserMapper;
import com.juyss.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @package com.juyss.service.impl
 * @project Spring-Security
 * @date 2021/12/16 1:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
}
