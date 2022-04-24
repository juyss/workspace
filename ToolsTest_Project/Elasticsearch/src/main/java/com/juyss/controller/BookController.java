package com.juyss.controller;

import com.juyss.dao.BookDao;
import com.juyss.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookController
 * @Desc:
 * @package com.juyss.controller
 * @project elasticsearch
 * @date 2020/12/30 19:12
 */
@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @GetMapping("/get_all")
    public Iterable<Book> getAll(){
        Book book = new Book();
        book.setId(1);
        book.setName("Java1");
        book.setPrice(12.3);
        book.setPublishedTime(new Date());
        bookDao.save(book);
        Iterable<Book> books = bookDao.findAll();
        books.forEach(System.out::println);
        return books;
    }
}
