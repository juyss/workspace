package com.juyss.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juyss.bean.TAdmin;
import com.juyss.bean.TMenu;
import com.juyss.service.AdminService;
import com.juyss.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class ServiceTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private AdminService adminService;

    @Test
    public void Test01(){
        List<TMenu> list = menuService.getMenuList();
        for (TMenu menu : list) {
            System.out.println(menu);
        }
    }

    @Test
    public void Test02(){
        PageHelper.startPage(3,5);
        List<TAdmin> list = adminService.getAdmin();
        PageInfo<TAdmin> info = new PageInfo<>(list, 5);
        List<TAdmin> adminList = info.getList();
        for (TAdmin admin : adminList) {
            System.out.println("用户信息=====>"+admin);
        }
    }

    @Test
    public void Test03(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(116);
        list.add(117);
        list.add(118);
        list.add(126);
        list.add(127);
        Boolean flag = adminService.deleteBatch(list);
        if (flag){
            System.out.println("==========================>删除成功,共删除"+list.size()+"条数据");
        }else {
            System.out.println("==========================>删除失败!!!");
        }
    }

}
