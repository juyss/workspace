package com.mengma.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: FirstSpring
 * @Desc:
 * @package com.mengma.ioc
 * @project SpringTest
 * @date 2020/6/5 16:23
 */
public class FirstSpring {

    public static void main(String[] args) {
        // 定义Spring配置文件的路径
        String xmlPath = "applicationContext.xml";

        // 初始化Spring容器，加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

        // 通过容器获取personDao实例
        PersonDao personDao = (PersonDao) applicationContext.getBean("PersonDAO");

        // 调用 personDao 的 add ()方法
        personDao.add();
    }

}
