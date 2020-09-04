package com.atguigu.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpMapper;

public class TestCRUD {

	@Test
	public void testCRUD() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		//SqlSession sqlSession = sqlSessionFactory.openSession();//需要手动处理事务
		SqlSession sqlSession = sqlSessionFactory.openSession(true);//自动处理事务
		EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
		//测试：根据eid获取员工信息
		/*Emp emp = empMapper.getEmpByEid("3");
		System.out.println(emp);*/
		//测试：获取所有的员工信息
		/*List<Emp> list = empMapper.getAllEmp();
		System.out.println(list);*/
		//测试：添加员工信息
		/*empMapper.addEmp(new Emp(null, "admin", 23, "女"));
		sqlSession.commit();//提交事务*/
		//测试：修改员工信息
		empMapper.updateEmp(new Emp(6, "张二", 33, "女"));
		//测试：删除员工信息
		/*Boolean i = empMapper.deleteEmp("2");
		System.out.println("result:"+i);*/
		//select 字段名 from 表名 where 条件 group by 字段名 having 条件 order by 字段名 desc/asc limit index,pageSize
	}
	
}
