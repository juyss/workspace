package com.juyss.service.impl;

import com.juyss.mapper.DepartmentMapper;
import com.juyss.pojo.Department;
import com.juyss.pojo.DepartmentExample;
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
        DepartmentExample example = new DepartmentExample();
        example.createCriteria();
        List<Department> departments = departmentMapper.selectByExample(example);
        System.out.println("查询部门结果==>"+departments);
        return departments;
    }

    /**
     * 添加部门信息
     *
     * @param department 封装部门信息对象
     * @return Boolean 是否添加成功
     */
    @Override
    public Boolean addDept(Department department) {
        boolean flag = false;

        int result = departmentMapper.insert(department);

        if (result==1){
            flag = true;
        }

        return flag;
    }

    /**
     * 删除部门信息
     *
     * @param deptId 要删除的部门id
     * @return Boolean 是否删除成功
     */
    @Override
    public Boolean deleteDept(Integer deptId) {
        boolean flag = false;

        int result = departmentMapper.deleteByPrimaryKey(deptId);

        if (result==1){
            flag = true;
        }

        return flag;
    }


}
