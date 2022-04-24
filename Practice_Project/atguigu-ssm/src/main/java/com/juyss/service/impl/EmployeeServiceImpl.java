package com.juyss.service.impl;

import com.juyss.mapper.EmployeeMapper;
import com.juyss.pojo.Employee;
import com.juyss.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: EmployeeServiceImpl
 * @Desc: ssm_emp表业务层接口实现类
 * @package com.juyss.service.impl
 * @project atguigu-ssm
 * @date 2020/9/27 14:38
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeMapper employeeMapper;

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }


    /**
     * 查询所有员工
     *
     * @return List<Employee> 员工列表
     */
    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 员工保存
     *
     * @param employee 保存的员工对象
     * @return Boolean 是否插入成功
     */
    @Override
    public Boolean saveEmp(Employee employee) {
        boolean flag = false;
        int i = employeeMapper.insert(employee);
        if (i == 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 检验用户名是否可用
     *
     * @param empName 要检验的用户名
     * @return true：代表当前姓名可用   false：不可用
     */
    @Override
    public boolean checkUser(String empName) {
        return false;
    }

    /**
     * 按照员工id查询员工
     *
     * @param id 要查询的ID
     * @return 查询到的员工
     */
    @Override
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKeyWithDept(id);
    }

    /**
     * 员工更新
     *
     * @param employee 要更新的Employee对象
     * @return Boolean 是否更新成功
     */
    @Override
    public Boolean updateEmp(Employee employee) {
        boolean flag = false;
        int i = employeeMapper.updateByPrimaryKey(employee);
        if (i == 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 员工删除
     *
     * @param id 要删除的员工ID
     * @return Boolean 是否删除成功
     */
    @Override
    public Boolean deleteEmp(Integer id) {
        boolean flag = false;
        int i = employeeMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 批量删除员工
     *
     * @param ids 员工ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {

    }
}
