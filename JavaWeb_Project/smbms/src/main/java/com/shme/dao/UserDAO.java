package com.shme.dao;

import com.shme.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserDAO
 * @Desc: 针对于user表的数据库操作接口
 * @package com.shme.com
 * @project smbms
 * @date 2020/6/23 9:48
 */
public interface UserDAO {

    /**
     * 获取用户登录信息
     *
     * @param userCode String
     * @return User 查询到的User对象
     */
    User getLoginUser(@Param("userCode") String userCode);


    /**
     * 根据用户ID修改密码
     *
     * @param id          int 要修改的用户的ID
     * @param newPassword String 新密码
     * @return int 受影响的行数
     */
    int updatePassword(@Param("newPassword") String newPassword, @Param("id") int id);

    /**
     * 获取数据库中符合指定条件的用户数量
     *
     * @param userName String 用户名
     * @param userRole String 用户角色
     * @return int 符合条件的用户个数
     */
    int getUserCount(@Param("userName") String userName,@Param("userRole") int userRole);

    /**
     * 获取单页的用户列表
     * @param userName String 用户名
     * @param userRole String 用户角色
     * @param offset int 当前页码
     * @param pageSize int 页面大小
     * @return
     */
    List<User> getUserList(@Param("userName") String userName, @Param("userRole") int userRole, @Param("offset") int offset, @Param("pageSize") int pageSize);

}
