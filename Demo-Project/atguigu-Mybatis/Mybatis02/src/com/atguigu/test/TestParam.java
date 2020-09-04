package com.atguigu.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpMapper;
import com.atguigu.mapper.ParamMapper;

public class TestParam {

	@Test
	public void testCRUD() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		//SqlSession sqlSession = sqlSessionFactory.openSession();//需要手动处理事务
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//自动处理事务
		ParamMapper mapper = sqlSession.getMapper(ParamMapper.class);
		/*Emp emp = new Emp(null, "admin", 23, "男");
		mapper.insertEmp(emp);
		System.out.println(emp.getEid());*/
		/*Emp emp = mapper.getEmpByEid("1");
		System.out.println(emp);*/
		/*Emp emp = mapper.getEmpByEidAndEname("1", "张三");
		System.out.println(emp);*/
		/*Map<String, Object> map = new HashMap<>();
		map.put("eid", "1");
		map.put("ename", "张三");
		Emp emp = mapper.getEmpByMap(map);*/
		Emp emp = mapper.getEmpByEidAndEnameByParam("1", "张三");
		System.out.println(emp);
	}
	
}
