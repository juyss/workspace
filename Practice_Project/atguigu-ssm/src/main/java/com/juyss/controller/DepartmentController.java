package com.juyss.controller;

import com.juyss.pojo.Department;
import com.juyss.service.DepartmentService;
import com.juyss.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DepartmentController
 * @Desc: ssm_dept表控制层
 * @package com.juyss.controller
 * @project atguigu-ssm
 * @date 2020/9/27 17:00
 */
@Controller
public class DepartmentController {

    private Logger log = Logger.getLogger(DepartmentController.class);

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 添加部门信息
     * @param department 部门信息封装对象
     * @param reatt 重定向属性
     * @return 携带提示信息,重定向到部门编辑页面:dept_edit.jsp
     */
    @RequestMapping(value = "/addDept",method = RequestMethod.POST)
    public String addDept(Department department, RedirectAttributes reatt){
        log.debug("========================准备执行添加部门信息操作======================");
        Boolean flag = departmentService.addDept(department);
        if (flag){
            reatt.addAttribute(Constant.MSG, "部门添加成功");
        }else {
            reatt.addAttribute(Constant.MSG, "部门添加失败");
        }
        return "redirect:/toDeptEdit/0";
    }

    /**
     * 删除部门信息
     * @param deptId 要删除的部门id
     * @return 携带提示信息,重定向到部门编辑页面:dept_edit.jsp
     */
    @RequestMapping("/deleteDept/{deptId}")
    public String deleteDept(@PathVariable("deptId")Integer deptId,
                             RedirectAttributes reatt){
        log.debug("========================准备执行删除部门信息操作======================");
        Boolean flag = departmentService.deleteDept(deptId);
        if (flag){
            reatt.addAttribute(Constant.MSG, "部门删除成功");
        }else {
            reatt.addAttribute(Constant.MSG, "部门删除失败");
        }
        return "redirect:/toDeptEdit/0";
    }
}
