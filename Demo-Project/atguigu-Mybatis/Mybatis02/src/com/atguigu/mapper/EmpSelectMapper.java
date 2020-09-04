package com.atguigu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.atguigu.bean.Emp;

public interface EmpSelectMapper {

	//根据eid查询一个员工信息
	Emp getEmpByEid(String eid);
	//获取所有的员工的数量
	Integer getCount();
	//以map集合获取一个员工信息
	Map<String, Object> getEmpMapByEid(String eid);
	//以map集合获取所有员工信息
	@MapKey("eid")//设置map的键，因为在查询时传出所有的员工信息，可以把员工信息作为值，但是必须设置键
	Map<String, Object> getAllEmpMap();
	
}
