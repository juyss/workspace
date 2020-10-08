package com.juyss.service.impl;

import com.juyss.mapper.DepartmentMapper;
import com.juyss.pojo.Department;
import com.juyss.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DepartmentServiceImpl
 * @Desc: ssm_dept表业务层接口实现类
 * @package com.juyss.service.impl
 * @project atguigu-ssm
 * @date 2020/9/27 14:43
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentMapper departmentMapper;

    @Autowired
    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 获取所有部门信息
     *
     * @return 部门信息集合
     */
    @Override
    public List<Department> getDepts() {
        return null;
    }
}
