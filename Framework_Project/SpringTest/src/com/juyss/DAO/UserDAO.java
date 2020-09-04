package com.juyss.DAO;

import comjuyss.pojo.User;

import java.util.ArrayList;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserDAO
 * @Desc:  操作User表的ADO接口
 * @package com.juyss
 * @project SpringTest
 * @date 2020/7/4 21:59
 */
public interface UserDAO {

    void insertUser(User user);

    void deleteUserById(int id);

    void updateUserById(int id);

    User getUserById(int id);

    ArrayList<User> getAll();

}
