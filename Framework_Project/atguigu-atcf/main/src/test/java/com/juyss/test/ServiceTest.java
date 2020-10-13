package com.juyss.test;

import com.juyss.bean.TMenu;
import com.juyss.service.MenuService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ServiceTest
 * @Desc: 业务层测试了类
 * @package com.juyss.test
 * @project atguigu-atcf
 * @date 2020/10/13 22:51
 */
public class ServiceTest {

    @Test
    public void Test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
        MenuService service = context.getBean(MenuService.class);
        List<TMenu> list = service.getMenuList();
        for (TMenu menu : list) {
            System.out.println(menu);
        }
    }

}
