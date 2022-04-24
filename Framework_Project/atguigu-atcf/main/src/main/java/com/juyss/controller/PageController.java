package com.juyss.controller;

import com.juyss.bean.TAdmin;
import com.juyss.bean.TMenu;
import com.juyss.service.AdminService;
import com.juyss.service.MenuService;
import com.juyss.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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

    private AdminService adminService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
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
    public String toMain(HttpSession session) {
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

        session.setAttribute("menu_list", pmenuList);

        log.debug("整理后的菜单:{}", pmenuList);

        return "main";
    }

    /**
     * @return 跳转到管理员列表第一页
     */
    @RequestMapping("/admin/index")
    public String toAdminIndex(){

        return "redirect:/admin/index/0";
    }

    /**
     * 跳转到管理员信息编辑页面
     * @param adminId 管理员对象id
     * @param model 数据模型
     * @return 跳转到：admin/edit.jsp
     */
    @RequestMapping("/admin/toEdit/{adminId}/{pageNum}")
    public String toAdminEdit(@PathVariable("adminId")Integer adminId,
                              @PathVariable("pageNum")Integer pageNum,
                              @RequestParam(value = "errormsg",required = false)String msg,
                             Model model){
        //如果存在页面信息，放入model中
        if (pageNum!=null){
            model.addAttribute(Const.PAGE_NUM, pageNum);
        }

        //如果存在提示信息，放入model中
        if (msg!=null){
            model.addAttribute(Const.ERROR_MSG, msg);
        }

        if (adminId!=null){
            //传入id为0，说明为添加操作
            if (adminId==0){
                return "admin/edit";
            }
            //传入id不为0，说明为修改操作需要回显
            TAdmin admin = adminService.selectAdmin(adminId);
            model.addAttribute("admin", admin);
            return "admin/edit";
        }
        return "redirect:/toIndex";
    }

    /**
     * 跳转到角色维护主页面
     * @return role/index.jsp
     */
    @RequestMapping("/role/index")
    public String toRoleIndex(){
        log.debug("准备跳转角色维护主页：/role/index.jsp");
        return "role/index";
    }

    /**
     * 跳转到菜单维护页面
     * @return menu/index.jsp
     */
    @RequestMapping("/menu/index")
    public String toMenuIndex(){
        log.debug("准备跳转菜单维护页面：/menu/index.jsp");
        return "menu/index";
    }

}
