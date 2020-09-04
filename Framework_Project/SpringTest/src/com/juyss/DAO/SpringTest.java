package com.juyss.DAO;

import comjuyss.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SpringTest
 * @Desc:  SpringFramework测试
 * @package com.juyss.DAO
 * @project SpringTest
 * @date 2020/7/4 22:31
 */
public class SpringTest {
        public static void main(String[] args) {
            // 定义Spring配置文件的路径,默认路径src下
            String xmlPath = "applicationContext.xml";

            // 初始化Spring容器，加载配置文件
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

            // 通过容器获取personDao实例
            UserDAO userdao = (UserDAO)applicationContext.getBean("UserDAO");

            // 调用 personDao 的 add ()方法
            userdao.insertUser(new User());
            userdao.deleteUserById(1);
            userdao.deleteUserById(1);
            userdao.getUserById(1);
            userdao.getAll();
        }
}
