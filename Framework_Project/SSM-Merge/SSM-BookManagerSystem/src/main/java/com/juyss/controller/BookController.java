package com.juyss.controller;

import com.alibaba.fastjson.JSON;
import com.juyss.pojo.Book;
import com.juyss.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookController
 * @Desc: 控制层
 * @package com.juyss.controller
 * @project SSM-Merge
 * @date 2020/9/13 20:16
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public BookController() {
        System.out.println("调用了BookController类无参构造器");
    }

    @RequestMapping("/books")
    public String getBookList(Model model){
        List<Book> books = bookService.getBookList();
        model.addAttribute("book_list",books);
        return "bookList";
    }

    //修改字符编码为utf-8
    @RequestMapping(value = "/json",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getJsonList(){
        List<Book> books = bookService.getBookList();
        return JSON.toJSONString(books);
    }

}
