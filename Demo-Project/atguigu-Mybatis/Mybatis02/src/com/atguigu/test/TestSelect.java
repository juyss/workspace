package com.atguigu.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpMapper;
import com.atguigu.mapper.EmpSelectMapper;

public class TestSelect {

	@Test
	public void testSelect() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		//SqlSession sqlSession = sqlSessionFactory.openSession();//需要手动处理事务
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//自动处理事务
		EmpSelectMapper mapper = sqlSession.getMapper(EmpSelectMapper.class);
		//若查询出的数据有多条，则绝对不能将接口中该方法的返回值设置为Javabean
		/*Emp emp = mapper.getEmpByEid("3");
		System.out.println(emp);*/
		//获取员工总记录数
		/*Integer i = mapper.getCount();
		System.out.println(i);*/
		//以map获取单个emp
		/*Map<String, Object> map = mapper.getEmpMapByEid("6");
		System.out.println(map);*/
		//以map获取所有emp
		Map<String, Object> map = mapper.getAllEmpMap();
		System.out.println(map);
	}
	
}
