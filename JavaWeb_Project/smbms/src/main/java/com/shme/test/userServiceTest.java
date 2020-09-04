package com.shme.test;

import com.shme.pojo.User;
import com.shme.service.UserService;
import com.shme.service.UserServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: userServiceTest
 * @Desc: UserService测试类
 * @package com.shme.test
 * @project smbms
 * @date 2020/8/29 17:28
 */
public class userServiceTest {
    private UserService userService = new UserServiceImpl();

    @Test
    public void loginTest(){
        String userCode= "liming";
        User user = userService.login(userCode);
        System.out.println("查到用户-->"+user);
    }

    @Test
    public void updatePasswordTest(){
        Boolean flag = userService.updatePassword("1564561", 6);
        System.out.println("结果:"+flag);
    }

    @Test
    public void getUserCountTest(){
        int count = userService.getUserCount("孙", 3);
        System.out.println(count);
    }

    @Test
    public void getUserListTest(){
        List<User> userList = userService.getUserList("", 0, 1, 5);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
