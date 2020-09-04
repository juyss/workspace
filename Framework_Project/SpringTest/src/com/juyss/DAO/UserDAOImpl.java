package com.juyss.DAO;

import comjuyss.pojo.User;

import java.util.ArrayList;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserDAOImpl
 * @Desc:  操作User表的具体实现类
 * @package com.juyss.DAO
 * @project SpringTest
 * @date 2020/7/4 22:15
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {
    @Override
    public void insertUser(User user) {
        System.out.println("调用了insert()方法");
    }

    @Override
    public void deleteUserById(int id) {
        System.out.println("调用了deleteUserById()方法");
    }

    @Override
    public void updateUserById(int id) {
        System.out.println("调用了updateUserById()方法");
    }

    @Override
    public User getUserById(int id) {
        System.out.println("调用了getUserById()方法");
        return null;
    }

    @Override
    public ArrayList<User> getAll() {
        System.out.println("调用了getAll()方法");
        return null;
    }
}
