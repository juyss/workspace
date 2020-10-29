package com.juyss.service;

import com.juyss.dao.UserDao;
import com.juyss.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserService
 * @Desc:
 * @package com.juyss.service
 * @project HelloWorld
 * @date 2020/10/29 21:20
 */
@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(Integer id){
        return userDao.getUser(id);
    }
}
