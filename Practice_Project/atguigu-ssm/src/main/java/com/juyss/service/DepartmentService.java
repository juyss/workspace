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

    List<Department> getDepts();

}
