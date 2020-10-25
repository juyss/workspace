package com.juyss.controller;

import com.juyss.pojo.Department;
import com.juyss.pojo.Employee;
import com.juyss.service.DepartmentService;
import com.juyss.service.EmployeeService;
import com.juyss.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PageController
 * @Desc: 控制页面跳转
 * @package com.juyss.controller
 * @project atguigu-ssm
 * @date 2020/10/12 15:39
 */
@Controller
public class PageController {

    private Logger log = Logger.getLogger(PageController.class);

    private EmployeeService employeeService;

    private DepartmentService departmentService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 跳转到部门编辑页面:dept_edit.jsp
     */
    @RequestMapping("/toDeptEdit/{pageNum}")
    public String toDeptEdit(@RequestParam(value = "msg", required = false) String msg,
                             @PathVariable(value = "pageNum",required = false) String pageNum,
                             Model model){
        log.debug("=====================准备跳转到dept_edit.jsp页面======================");
        //查询所有部门信息
        List<Department> deptList = departmentService.getDepts();
        model.addAttribute("dept_list", deptList);

        //传入信息
        if (msg != null) {
            model.addAttribute(Constant.MSG, msg);
        }

        //传入页码
        if (pageNum!=null){
            model.addAttribute("page_num", pageNum);
        }

        return "dept_edit";
    }

    /**
     * 跳转到员工信息编辑页面:emp_edit.jsp
     */
    @RequestMapping("/toEmpEdit")
    public String toEmpEdit(@RequestParam(value = "msg", required = false) String msg,
                            @RequestParam(value = "empId", required = false) String empId,
                            @RequestParam(value = "pageNum", required = false) String pageNum,
                            Model model) {
        log.debug("=============================准备跳转页面emp_edit.jsp页面============================");
        List<Department> deptList = departmentService.getDepts();
        model.addAttribute("dept_list", deptList);

        if (pageNum!=null){  //如果有pageNum就放到请求域中
            System.out.println("获取到跳转前的页码====>"+pageNum);
            model.addAttribute("page_num", pageNum);
        }

        if (msg != null) {   //如果有信息就设置到请求域中,说明已经对信息操作过
            model.addAttribute(Constant.MSG, msg);
            return "emp_edit";
        }

        if (empId != null) {  //如果有empId就查询用户并放到请求域中,说明是员工信息编辑请求
            int id = Integer.parseInt(empId);
            Employee emp = employeeService.getEmp(id);
            System.out.println("查询到的用户信息===>" + emp);
            model.addAttribute("emp_info", emp);
        }

        return "emp_edit";
    }

}
