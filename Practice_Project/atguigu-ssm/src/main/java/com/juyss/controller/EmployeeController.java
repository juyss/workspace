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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "page_num" ,defaultValue = "1")Integer pageNum, Model model){
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
        List<Employee> list = employeeService.getAll();
        PageInfo<Employee> pageInfo = new PageInfo<>(list, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "emp_list";
    }
}
