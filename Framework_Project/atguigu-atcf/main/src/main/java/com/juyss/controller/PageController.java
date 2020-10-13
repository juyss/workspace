package com.juyss.controller;

import com.juyss.bean.TMenu;
import com.juyss.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PageController
 * @Desc: 页面跳转Controller, 主要负责处理页面跳转
 * @package com.juyss.controller
 * @project atguigu-CrowdFunding
 * @date 2020/10/11 14:24
 */
@Controller
public class PageController {

    private final Logger log = LoggerFactory.getLogger(PageController.class);

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * @return 跳转到网站主页面
     */
    @RequestMapping("/toIndex")
    public String toIndex() {
        log.debug("准备跳转到主页面==>index.jsp");
        return "index";
    }

    /**
     * @return 跳转到登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        log.debug("准备跳转到登陆页面==>login.jsp");
        return "login";
    }

    /**
     * @return 跳转到注册页面
     */
    @RequestMapping("/toSignin")
    public String toSignin() {
        log.debug("准备跳转到注册页面==>signin.jsp");
        return "signin";
    }

    /**
     * @return 跳转到后台管理主页面,
     */
    @RequestMapping("/toMain")
    public String toMain(Model model) {
        log.debug("准备跳转到管理员主页面==>main.jsp");

        //获取所有菜单信息
        List<TMenu> list = menuService.getMenuList();
        log.debug("获取到菜单集合:{}", list);

        //创建新的集合存放父级菜单
        ArrayList<TMenu> pmenuList = new ArrayList<>();

        // 如果pid等于0说明此菜单为父级菜单
        for (TMenu menu : list) {
            if (menu.getPid() == 0) {
                pmenuList.add(menu);
            }
        }

        // 遍历父级菜单集合，然后每次遍历时遍历所有菜单信息集合，将对应的子级菜单放入对应父级菜单对象
        for (TMenu pmenu : pmenuList) {
            ArrayList<TMenu> cmenuList = new ArrayList<>();
            for (TMenu cMenu : list) {
                if (Objects.equals(pmenu.getId(), cMenu.getPid())) {
                    cmenuList.add(cMenu);
                }
            }
            pmenu.setChildren(cmenuList);
        }

        model.addAttribute("menu_list", pmenuList);

        log.debug("整理后的菜单:{}", pmenuList);

        return "main";
    }

}
