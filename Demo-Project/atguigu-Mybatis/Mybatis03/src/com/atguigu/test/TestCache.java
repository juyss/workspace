package com.atguigu.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.Emp;
import com.atguigu.mapper.EmpMapper;

public class TestCache {

	@Test
	public void testSecondCache() throws Exception {
		
		/**
		 * mybatis的二级缓存默认不开启，需要设置：
		 * 1、在核心配置文件<settings>中，加入配置：<setting name="cacheEnabled" value="true"/>
		 * 2、需要使用二级缓存的映射文件处使用cache配置缓存<cache />
		 * 3、注意：POJO需要实现Serializable接口
		 * 注意：二级缓存在 SqlSession 关闭或提交之后才会生效
		 * 1)全局setting的cacheEnable：
			配置二级缓存的开关，一级缓存一直是打开的
		 * 2)select标签的useCache属性：
			配置这个select是否使用二级缓存。一级缓存一直是使用的
		 * 3)sql标签的flushCache属性：
			增删改默认flushCache=true。sql执行以后，会同时清空一级和二级缓存。
			查询默认 flushCache=false。
		 * 4)sqlSession.clearCache()：只是用来清除一级缓存
		 */
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		EmpMapper mapper1 = sqlSession.getMapper(EmpMapper.class);
		Emp emp1 = mapper1.getEmpByEid("14");
		System.out.println(emp1);
		sqlSession.commit();
		System.out.println("==============");
		EmpMapper mapper2 = sqlSession.getMapper(EmpMapper.class);
		Emp emp2 = mapper2.getEmpByEid("15");
		System.out.println(emp2);
		
		sqlSession.commit();
		System.out.println("==============");
		EmpMapper mapper3 = sqlSession.getMapper(EmpMapper.class);
		Emp emp3 = mapper3.getEmpByEid("16");
		System.out.println(emp3);
		
		sqlSession.commit();
		System.out.println("==============");
		EmpMapper mapper4 = sqlSession.getMapper(EmpMapper.class);
		Emp emp4 = mapper4.getEmpByEid("17");
		System.out.println(emp4);
		
		sqlSession.commit();
		System.out.println("==============");
		EmpMapper mapper5 = sqlSession.getMapper(EmpMapper.class);
		Emp emp5 = mapper5.getEmpByEid("14");
		System.out.println(emp5);
		
		
		
	}
	
	
	@Test
	public void testFirstCache() throws Exception {
		
		/**
		 * mybatis中的一级缓存默认开启，是SqlSession级别的
		 * 即同一个SqlSession对于一个sql语句，执行之后就会存储在缓存中，下次执行相同的sql，直接从缓存中取
		 * 一级缓存失效的情况：
		 * 不同的SqlSession对应不同的一级缓存
		 * 同一个SqlSession但是查询条件不同
		 * 同一个SqlSession两次查询期间执行了任何一次增删改操作，会自动将缓存清空
		 * 同一个SqlSession两次查询期间手动清空了缓存
		 */
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
		EmpMapper mapper1 = sqlSession1.getMapper(EmpMapper.class);
		Emp emp1 = mapper1.getEmpByEid("14");
		System.out.println(emp1);
		
		
		sqlSession1.clearCache();
		System.out.println("+++++++++++++++++++++++++++++++");
		//mapper1.deleteMoreEmp("1");
		
		SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
		EmpMapper mapper2 = sqlSession1.getMapper(EmpMapper.class);
		Emp emp2 = mapper2.getEmpByEid("14");
		System.out.println(emp2);
		
	}
	
	
	
	
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		return sqlSessionFactory;
	}
	
}
