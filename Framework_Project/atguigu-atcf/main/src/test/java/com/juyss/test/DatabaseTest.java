package com.juyss.test;

import com.juyss.bean.TAdmin;
import com.juyss.mapper.TAdminMapper;
import com.juyss.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DatabaseTest
 * @Desc: 数据库添加数据
 * @package com.juyss.test
 * @project atguigu-atcf
 * @date 2020/10/14 18:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class DatabaseTest {

    @Autowired
    private TAdminMapper mapper;

    @Autowired
    private TAdmin admin;

    @Test
    public void Test01(){
        try {
            for (int i = 0; i < 100; i++) {
                admin.setLoginacct("admin"+i);
                admin.setUsername("管理员"+i);
                admin.setUserpswd(MD5Util.digest("123456"));
                admin.setEmail("admin"+i+"@163.com");
                admin.setCreatetime("2020-10-15 23:00:59");
                mapper.insert(admin);
                System.out.println("第"+i+"条数据添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
