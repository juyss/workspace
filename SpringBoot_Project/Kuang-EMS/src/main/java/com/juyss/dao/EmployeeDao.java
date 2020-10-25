package com.juyss.dao;

import com.juyss.pojo.Department;
import com.juyss.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: EmployeeDao
 * @Desc: 模拟员工数据
 * @package com.juyss.dao
 * @project Kuang-EMS
 * @date 2020/10/23 13:05
 */
public class EmployeeDao {

    private static final Map<Integer,Employee> emps;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        emps = new HashMap<>();

        emps.put(1001,new Employee(1001,"AA","AAA@163.com",0,new Department(101, "教学部")));
        emps.put(1002,new Employee(1002,"BB","BBB@163.com",0,new Department(101, "教学部")));
        emps.put(1003,new Employee(1003,"CC","CCC@163.com",0,new Department(101, "教学部")));
        emps.put(1004,new Employee(1004,"DD","DDD@163.com",0,new Department(101, "教学部")));
        emps.put(1005,new Employee(1005,"EE","EEE@163.com",0,new Department(101, "教学部")));
    }

    private static Integer initId = 1006;

    //增加员工并保存
    public void save(Employee employee){
        if (employee.getId()!=null){
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.selectDept(employee.getDepartment().getId()));

        emps.put(employee.getId(), employee);

    }

    //查询所有员工信息
    public Collection<Employee> getAllEmployee(){
        return emps.values();
    }

    //通过id查询员工
    public Employee selectEmployee(Integer Id){
        return emps.get(Id);
    }

    //删除员工
    public Employee deleteEmployee(Integer id){
        return emps.remove(id);
    }

}
