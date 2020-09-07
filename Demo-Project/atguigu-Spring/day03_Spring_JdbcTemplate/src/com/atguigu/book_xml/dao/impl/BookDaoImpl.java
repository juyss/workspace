package com.atguigu.book_xml.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atguigu.book_xml.dao.BookDao;
import com.atguigu.book_xml.exception.MyException;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer selectPrice(String bid) {
		Integer price = jdbcTemplate.queryForObject("select price from book where bid = ?", new Object[] {bid}, Integer.class);
		return price;
	}

	@Override
	public void updateSt(String bid) {
		//获取该书籍的库存
		Integer st = jdbcTemplate.queryForObject("select st from stock where sid = ?", new Object[] {bid}, Integer.class);
		if(st <= 0) {
			throw new RuntimeException();
		}else {
			jdbcTemplate.update("update stock set st = st -1 where sid = ?", bid);
		}
		
	}

	@Override
	public void updateBalance(String uid, Integer price) {
		Integer balance = jdbcTemplate.queryForObject("select balance from money where uid = ?", new Object[] {uid}, Integer.class);
		if(balance < price) {
			throw new MyException("余额不足");
		}else {
			jdbcTemplate.update("update money set balance = balance - ? where uid = ?", price, uid);
		}
	}
	
	
	
}
