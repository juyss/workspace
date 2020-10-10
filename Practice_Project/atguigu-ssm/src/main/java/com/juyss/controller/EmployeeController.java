package com.juyss.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juyss.pojo.Employee;
import com.juyss.service.EmployeeService;
import com.juyss.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: EmployeeController
 * @Desc: 员工 业务层逻辑
 * @package com.juyss.controller
 * @project atguigu-ssm
 * @date 2020/9/27 15:30
 */
@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 查询所有员工信息并进行分页
     * @param pageNum 当前页码
     * @param model 数据模型
     * @return 返回页面page /WEB-INF/emp_list.jsp
     */
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "page_num" ,defaultValue = "1")Integer pageNum,
                          @RequestParam(value = "msg",required = false)String msg,
                          Model model){
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
        List<Employee> list = employeeService.getAll();
        PageInfo<Employee> pageInfo = new PageInfo<>(list, 5);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute(Constant.MSG, msg);
        return "emp_list";
    }


    @RequestMapping("/update")
    public String updateEmp(){

        return "emp_list";
    }
}
