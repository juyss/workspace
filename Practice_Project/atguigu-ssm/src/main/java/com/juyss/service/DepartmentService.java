package com.juyss.service;

import com.juyss.pojo.Department;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DepartmentService
 * @Desc: ssm_dept表业务层接口
 * @package com.juyss.service
 * @project atguigu-ssm
 * @date 2020/9/27 14:41
 */
public interface DepartmentService {

    /**
     * 获取所有部门信息
     *
     * @return 部门信息集合
     */
    List<Department> getDepts();

    /**
     * 添加部门信息
     * @param department 封装部门信息对象
     * @return Boolean 是否添加成功
     */
    Boolean addDept(Department department);

}
