package com.juyss.dao;

import com.juyss.pojo.Department;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DepartmentDao
 * @Desc: 模拟部门查询结果
 * @package com.juyss.dao
 * @project Kuang-EMS
 * @date 2020/10/23 13:05
 */
public class DepartmentDao {

    private static final Map<Integer, Department> depts;

    //初始化depts
    static{
        depts = new HashMap<>();

        depts.put(101,new Department(101, "教学部"));
        depts.put(102,new Department(102, "市场部"));
        depts.put(103,new Department(103, "教研部"));
        depts.put(104,new Department(104, "运营部"));
        depts.put(105,new Department(105, "后勤部"));
    }

    //获取部门信息
    public Collection<Department> getAllDepartment(){

        return depts.values();
    }

    //通过id查询部门
    public Department selectDept(Integer id){
        return depts.get(id);
    }

}
