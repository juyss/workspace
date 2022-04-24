package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.Dept;
import com.atguigu.bean.Emp;

public interface EmpDeptMapper {

	List<Emp> getAllEmp();
	
	Emp getEmpStep(String eid);
	
	Dept getDeptEmpsByDid(String did);
	
	Dept getOnlyDeptByDid(String did);
	
	List<Emp> getEmpListByDid(String did);
	
}
