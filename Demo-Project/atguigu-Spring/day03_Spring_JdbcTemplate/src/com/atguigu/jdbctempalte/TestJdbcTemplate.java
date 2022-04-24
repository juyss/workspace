package com.atguigu.jdbctempalte;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TestJdbcTemplate {

	ApplicationContext ac = new ClassPathXmlApplicationContext("jdbc.xml");
	JdbcTemplate jdbcTemplate = ac.getBean("jdbcTemplate", JdbcTemplate.class);

	@Test
	public void testUpdate() {
		//jdbcTemplate.update("insert into emp values(null,'张三',23,'男')");
		/*String sql = "insert into emp values(null, ?, ?, ?)";
		jdbcTemplate.update(sql, "李四", 24, "女");//单个增删改*/	
		
		String eids = "3,4,5";
		String sql = "delete from emp where eid in ("+eids+")";
		jdbcTemplate.update(sql);
		
		String mohu = "a";
		String sqls = "select * from emp where ename like '%?%'";//不能使用
		String sqlss = "select * from emp where ename like concat('%', ? ,'%')";//可以使用
		
	}
	
	@Test
	public void testBatchUpdate() {
		String sql = "insert into emp values(null, ?, ?, ?)";
		List<Object[]> list = new ArrayList<>();
		list.add(new Object[] {"a1", 1, "男"});
		list.add(new Object[] {"a2", 2, "男"});
		list.add(new Object[] {"a3", 3, "男"});
		jdbcTemplate.batchUpdate(sql, list);//批量增删改
	}

	@Test
	public void testQueryForObject() {
		//jdbcTemplate.queryForObject(sql, requiredType)用来获取单个的值
		//jdbcTemplate.queryForObject(sql, rowMapper)用来获取单条数据
		/*String sql = "select eid,ename,age,sex from emp where eid = ?";
		RowMapper<Emp> rowMapper = new BeanPropertyRowMapper<>(Emp.class);//将列名（字段名或字段名的别名）与属性名进行映射
		Emp emp = jdbcTemplate.queryForObject(sql, new Object[] {7}, rowMapper);
		System.out.println(emp);*/
		
		String sql = "select count(*) from emp";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(count);
	}
	
	@Test
	public void testQuery() {
		String sql = "select eid,ename,age,sex from emp";
		RowMapper<Emp> rowMapper = new BeanPropertyRowMapper<>(Emp.class);
		List<Emp> list = jdbcTemplate.query(sql, rowMapper);
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
}
