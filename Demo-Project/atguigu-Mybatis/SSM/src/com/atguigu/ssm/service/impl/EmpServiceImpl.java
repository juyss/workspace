package com.atguigu.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.ssm.bean.Dept;
import com.atguigu.ssm.bean.Emp;
import com.atguigu.ssm.mapper.DeptMapper;
import com.atguigu.ssm.mapper.EmpMapper;
import com.atguigu.ssm.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;
	
	@Autowired
	private DeptMapper deptMapper;

	@Override
	public List<Emp> getAllEmp() {
		return empMapper.getAllEmp();
	}

	@Override
	public Emp getEmpByEid(String eid) {
		return empMapper.getEmpByEid(eid);
	}

	@Override
	public void updateEmp(Emp emp) {
		empMapper.updateEmp(emp);
	}
	
	@Override
	public List<Dept> getAllDept() {
		return deptMapper.getAllDept();
	}
	
}
