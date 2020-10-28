package com.itheima.dao.system;

import com.itheima.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-26 8:55
 */
public interface UserDao {
    int save(User user);

    int delete(User user);

    int update(User user);

    User findById(String id);

    List<User> findAll();

    void deleteRole(String userId);

    void updateRole(@Param("userId") String userId, @Param("roleId")String roleId);

    User findByEmailAndPwd(@Param("email")String email,@Param("password") String password);
}
