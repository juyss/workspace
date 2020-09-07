package com.atguigu.book_xml.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.book_xml.service.BookService;
import com.atguigu.book_xml.service.Cashier;

@Service
public class CashierServiceImpl implements Cashier {

	@Autowired
	private BookService service;
	
	@Override
	public void checkOut(String uid, List<String> bids) {
		for (String bid : bids) {
			service.buyBook(bid, uid);
		}
	}

}
