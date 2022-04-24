package com.atguigu.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpMapper;

public class TestSQL {

	
	@Test
	public void testMore() throws Exception {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
		
		Emp emp1 = new Emp(17, "a1", 233, "女");
		Emp emp2 = new Emp(18, "aa1", 233, "女");
		Emp emp3 = new Emp(19, "aaa1", 233, "女");
		Emp[] emps = {emp1, emp2, emp3};
		mapper.updateMoreByArray(emps);
		
		
		/*//Emp[] emps = new Emp[3];
		//Emp[] emps = new Emp[] {};
		Emp emp1 = new Emp(null, "a", 23, "男");
		Emp emp2 = new Emp(null, "aa", 23, "男");
		Emp emp3 = new Emp(null, "aaa", 23, "男");
		Emp[] emps = {emp1, emp2, emp3};
		
		mapper.insertMoreByArray(emps);*/
		
		/*List<Integer> eids = new ArrayList<>();
		eids.add(7);
		eids.add(8);
		eids.add(10);
		mapper.deleteMoreByList(eids);*/
		
	}
	
	@Test
	public void testDeleteMore() throws Exception {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
		String eids = "3,4,6";
		mapper.deleteMoreEmp(eids);
		
	}
	
	@Test
	public void testChoose() throws Exception {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
		Emp emp = new Emp();
		
		//emp.setEid(1);
		emp.setEname("张三三");
		emp.setAge(23);
		emp.setSex("123");
		
		mapper.insertEmp(emp);
		
		/*List<Emp> list = mapper.getEmpListByChoose(emp);
		for (Emp e : list) {
			System.out.println(e);
		}*/
		
	}
	
	@Test
	public void testIf() throws Exception {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
		Emp emp = new Emp();
		
		//emp.setEid(1);
		emp.setEname("张三");
		emp.setAge(23);
		//emp.setSex("1");
		
		List<Emp> list = mapper.getEmpListByMoreTJ(emp);
		for (Emp e : list) {
			System.out.println(e);
		}
		
	}
	
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		return sqlSessionFactory;
	}
	
}
