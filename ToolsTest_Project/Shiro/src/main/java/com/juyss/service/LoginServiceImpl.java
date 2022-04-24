package com.juyss.service;

import com.juyss.pojo.Permission;
import com.juyss.pojo.Role;
import com.juyss.pojo.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: LoginServiceImpl
 * @Desc: 模拟数据库查询操作
 * @package com.juyss.service
 * @project Shiro
 * @date 2021/1/1 17:50
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Override
    public User getUserByName(String getMapByName) {
        return getMapByName(getMapByName);
    }


    /**
     * 模拟数据库查询
     *
     * @param userName 用户名
     * @return User
     */
    private User getMapByName(String userName) {
        //创建两个权限
        Permission Permission1 = new Permission("1", "query");
        Permission Permission2 = new Permission("2", "add");
        Set<Permission> PermissionSet = new HashSet<>();
        PermissionSet.add(Permission1);
        PermissionSet.add(Permission2);

        //创建角色,并为角色添加权限
        Role role = new Role("1", "admin", PermissionSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        //创建用户,为用户添加角色
        User user = new User("1", "wsl", "123456", roleSet);
        Map<String, User> map = new HashMap<>();
        map.put(user.getUserName(), user); //将创建好的用户放入Map

        Set<Permission> PermissionSet1 = new HashSet<>();
        PermissionSet1.add(Permission1);
        Role role1 = new Role("2", "user", PermissionSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User("2", "zhangsan", "123456", roleSet1);
        map.put(user1.getUserName(), user1); //将创建好的用户放入Map

        return map.get(userName); //获取指定用户名的ID
    }
}
