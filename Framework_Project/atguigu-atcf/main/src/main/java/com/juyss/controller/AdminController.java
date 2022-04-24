package com.juyss.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juyss.bean.TAdmin;
import com.juyss.bean.TAdminExample;
import com.juyss.service.AdminService;
import com.juyss.util.AppDateUtils;
import com.juyss.util.Const;
import com.juyss.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AdminController
 * @Desc: 管理员模块，用户管理页面
 * @package com.juyss.controller
 * @project atguigu-atcf
 * @date 2020/10/14 14:23
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    /**
     * 获取分页后的管理员列表，分页信息
     * @return 返回管理员信息列表展示页面：/admin/index.jsp
     */
    @RequestMapping("/index/{pageNum}")
    public String getAdmin(@PathVariable(value = "pageNum")Integer pageNum,
                           @RequestParam(value = Const.ERROR_MSG,required = false)String msg,
                           Model model){

        if (msg!=null){
            model.addAttribute(Const.ERROR_MSG,msg);
        }

        PageHelper.startPage(pageNum, Const.PAGE_SIZE);
        List<TAdmin> adminList = service.getAdmin();
        PageInfo<TAdmin> pageInfo = new PageInfo<>(adminList,Const.NAVIGATE_PAGES);

        model.addAttribute("page_info", pageInfo);
        log.debug("分页信息：{}",pageInfo);
        List<TAdmin> list = pageInfo.getList();

        model.addAttribute("admin_list", list);
        log.debug("用户列表：{}",list);

        return "admin/index";
    }

    /**
     * POST请求为添加操作
     * @return 返回页面：admin/edit.jsp
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String postAdd(TAdmin admin,
                          RedirectAttributes reatt){
        log.debug("进入add(),POST请求,执行添加操作================");
        log.debug("获取的表单信息================{}",admin);
        admin.setUserpswd(MD5Util.digest(admin.getUserpswd()));
        String time = AppDateUtils.getFormatTime();
        log.debug("获取系统时间：{}",time);
        admin.setCreatetime(time);
        Boolean flag = service.insertAdmin(admin);
        if (flag){
            reatt.addAttribute(Const.ERROR_MSG, admin.getUsername()+"==>添加成功！！！");
        }else {
            reatt.addAttribute(Const.ERROR_MSG, admin.getUsername()+"==>添加失败！！！");
        }
        return "redirect:/admin/toEdit/0/"+Integer.MAX_VALUE;
    }

    /**
     * PUT请求为修改操作
     * @return 返回修改的数据所在页面：admin/index.jsp
     */
    @RequestMapping(value = "/add",method = RequestMethod.PUT)
    public String putAdd(@RequestParam("page_num")Integer pageNum,
                         TAdmin admin,
                         RedirectAttributes reatt){
        log.debug("进入add(),PUT请求，执行修改操作================");
        admin.setUserpswd(MD5Util.digest(admin.getUserpswd()));
        Boolean flag = service.updateAdmin(admin);
        if (flag){
            reatt.addAttribute(Const.ERROR_MSG, admin.getUsername()+"==>修改成功！！！");
        }else {
            reatt.addAttribute(Const.ERROR_MSG, admin.getUsername()+"==>修改失败！！！");
        }
        return "redirect:/admin/index/"+pageNum;
    }

    /**
     * 删除指定ID的admin数据
     * @param adminId 要删除的admin数据id
     * @param pageNum 跳转页码
     * @return 返回主页：admin/index.jsp
     */
    @RequestMapping("/delete/{adminId}/{pageNum}")
    public String deleteSingle(@PathVariable("adminId") Integer adminId,
                               @PathVariable("pageNum") Integer pageNum,
                               RedirectAttributes reatt){
        log.debug("进入deleteSingle(),删除的admin信息===>{}",adminId);
        Boolean flag = service.deleteSingle(adminId);
        if (flag){
            reatt.addAttribute(Const.ERROR_MSG, adminId+"==>删除成功！！！");
        }else {
            reatt.addAttribute(Const.ERROR_MSG, adminId+"==>删除失败！！！");
        }
        return "redirect:/admin/index/"+pageNum;
    }

    @RequestMapping("delete_batch/{ids}/{pageNum}")
    public String deleteBatch(@PathVariable("pageNum") Integer pageNum,
                              @PathVariable("ids")String ids,
                              RedirectAttributes reatt){
        log.debug("参数获取：{}",ids);
        String[] adminList = ids.split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (String idStr : adminList) {
            int id = Integer.parseInt(idStr);
            list.add(id);
        }
        Boolean flag = service.deleteBatch(list);
        if (flag){
            reatt.addAttribute(Const.ERROR_MSG, list.size()+"条数据==>删除成功！！！");
        }else {
            reatt.addAttribute(Const.ERROR_MSG, list.size()+"条数据==>删除失败！！！");
        }
        return "redirect:/admin/index/"+pageNum;
    }

    /**
     * 按条件模糊查询
     * @param condition 条件
     * @param pageNum 跳转页码
     * @return 返回显示查询结果页面：admin/index.jsp
     */
    @RequestMapping("/select/{pageNum}")
    public String selectByExample(@RequestParam("query_condition")String condition,
                                  @PathVariable("pageNum")Integer pageNum,
                                  Model model){
        condition = "%"+condition+"%";
        //创建查询条件
        TAdminExample example = new TAdminExample();
        example.createCriteria().andLoginacctLike(condition);
        TAdminExample example1 = new TAdminExample();
        TAdminExample.Criteria criteria1 = example1.createCriteria().andUsernameLike(condition);
        TAdminExample example2 = new TAdminExample();
        TAdminExample.Criteria criteria2 = example2.createCriteria().andEmailLike(condition);
        example.or(criteria1);
        example.or(criteria2);

        //按条件查询并分页
        PageHelper.startPage(pageNum,Const.PAGE_SIZE);
        List<TAdmin> adminList = service.selectByExample(example);
        PageInfo<TAdmin> pageInfo = new PageInfo<>(adminList,Const.NAVIGATE_PAGES);
        model.addAttribute("page_info", pageInfo);
        log.debug("分页信息：{}",pageInfo);
        List<TAdmin> list = pageInfo.getList();

        model.addAttribute("admin_list", list);
        log.debug("用户列表：{}",list);

        return "admin/index";
    }

}
