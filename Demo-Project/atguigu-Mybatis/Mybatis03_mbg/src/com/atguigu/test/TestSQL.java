package com.atguigu.test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.atguigu.bean.Emp;
import com.atguigu.bean.EmpExample;
import com.atguigu.bean.EmpExample.Criteria;
import com.atguigu.mapper.EmpMapper;

public class TestSQL {

	
	@Test
	public void testCRUD() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
		EmpExample example = new EmpExample();
		
		Criteria c1 = example.createCriteria();
		c1.andEnameLike("%a%");
		c1.andSexEqualTo("1");
		
		Criteria c2 = example.createCriteria();
		c2.andDidEqualTo(2);
		
		example.or(c2);
		
		List<Emp> list = mapper.selectByExample(example);
		for (Emp emp : list) {
			System.out.println(emp);
		}
		
	}
	
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		return sqlSessionFactory;
	}
	
	@Test
	public void testMBG() throws Exception {
	   List<String> warnings = new ArrayList<String>();
	   boolean overwrite = true;
	   File configFile = new File("mbg.xml");
	   ConfigurationParser cp = new ConfigurationParser(warnings);
	   Configuration config = cp.parseConfiguration(configFile);
	   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
	   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
	   myBatisGenerator.generate(null);
	}
	
}
