package com.juyss.service;

import com.juyss.pojo.Employee;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: EmployeeService
 * @Desc: ssm_emp表业务层接口
 * @package com.juyss.service
 * @project atguigu-ssm
 * @date 2020/9/27 14:31
 */
public interface EmployeeService {
    /**
     * 查询所有员工
     * @return List<Employee> 员工列表
     */
    List<Employee> getAll();

    /**
     * 员工保存
     * @param employee 保存的员工对象
     * @return
     */
    Boolean saveEmp(Employee employee);

    /**
     * 检验用户名是否可用
     *
     * @param empName 要检验的用户名
     * @return  true：代表当前姓名可用   false：不可用
     */
    boolean checkUser(String empName);

    /**
     * 按照员工id查询员工
     * @param id 要查询的ID
     * @return 查询到的员工
     */
    Employee getEmp(Integer id);

    /**
     * 员工更新
     * @param employee 要更新的Employee对象
     * @return
     */
    Boolean updateEmp(Employee employee);

    /**
     * 员工删除
     * @param id 要删除的员工ID
     * @return
     */
    Boolean deleteEmp(Integer id);

    /**
     * 批量删除员工
     * @param ids 员工ID集合
     */
    void deleteBatch(List<Integer> ids);

}
