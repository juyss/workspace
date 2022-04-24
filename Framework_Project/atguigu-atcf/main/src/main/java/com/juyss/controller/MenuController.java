package com.juyss.controller;

import com.juyss.bean.TMenu;
import com.juyss.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MenuController
 * @Desc: 菜单维护模块控制层
 * @package com.juyss.controller
 * @project atguigu-atcf
 * @date 2020/10/19 10:18
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private final Logger log = LoggerFactory.getLogger(PageController.class);

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @ResponseBody
    @RequestMapping("/menus")
    public List<TMenu> getMenuList(){
        List<TMenu> menuList = menuService.getMenuList();
        log.debug("查询到菜单集合信息：{}",menuList);
        return menuList;
    }

}
