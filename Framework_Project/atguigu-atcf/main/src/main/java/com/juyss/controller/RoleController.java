package com.juyss.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juyss.bean.TRole;
import com.juyss.bean.TRoleExample;
import com.juyss.service.RoleService;
import com.juyss.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RoleController
 * @Desc: 处理角色维护模块请求
 * @package com.juyss.controller
 * @project atguigu-atcf
 * @date 2020/10/17 18:04
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private Logger log = LoggerFactory.getLogger(RoleController.class);

    private RoleService service;

    @Autowired
    public void setService(RoleService service) {
        this.service = service;
    }

    /**
     * 查询所有角色数据
     * @param pageNum 起始页码
     * @return PageInfo<TRole> 分页数据
     */
    @ResponseBody
    @RequestMapping("/roles")
    public PageInfo<TRole> getRoles(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum){
        TRoleExample example = new TRoleExample();
        example.createCriteria();
        PageHelper.startPage(pageNum,Integer.MAX_VALUE);
        List<TRole> roleList = service.selectByExample(example);
        PageInfo<TRole> pageInfo = new PageInfo<>(roleList, Const.NAVIGATE_PAGES);
        log.debug("返回JSON分页数据：{}",pageInfo);
        return pageInfo;
    }

    /**
     * 单个删除角色信息
     * @param roleId 要删除的id
     * @return 返回页面：role/index.jsp
     */
    @RequestMapping("/delete/{roleId}")
    public String deleteSingle(@PathVariable("roleId")Integer roleId,
                               Model model){
        log.debug("准备执行删除操作，删除的id===>{}",roleId);
        Boolean flag = service.deleteSingle(roleId);
        if (flag){
            model.addAttribute(Const.ERROR_MSG, roleId+"==>删除成功！！！");
        }else {
            model.addAttribute(Const.ERROR_MSG, roleId+"==>删除失败！！！");
        }
        return "role/index";
    }

    /**
     * 批量删除角色信息
     * @param roleIds 角色信息id字符串
     * @return 返回页面：role/index.jsp
     */
    @RequestMapping("/deleteBatch/{roleIds}")
    public String deleteBatch(@PathVariable("roleIds")String roleIds,
                              Model model){
        log.debug("准备执行批量删除操作，删除的ids==>{}",roleIds);
        String[] split = roleIds.split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : split) {
            int i = Integer.parseInt(s);
            list.add(i);
        }
        Boolean flag = service.deleteBatch(list);
        if (flag){
            model.addAttribute(Const.ERROR_MSG, "批量删除成功！！！");
        }else {
            model.addAttribute(Const.ERROR_MSG, "批量删除失败！！！");
        }
        return "role/index";
    }


}
