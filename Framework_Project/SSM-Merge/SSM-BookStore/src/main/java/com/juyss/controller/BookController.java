package com.juyss.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juyss.pojo.Book;
import com.juyss.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookController
 * @Desc:
 * @package com.juyss.controller
 * @project SSM-Merge
 * @date 2020/9/20 17:06
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 查询所有图书(分页后)
     * @param model 模型
     * @return 分页后的图书集合,和分页信息
     */
    @RequestMapping(value = "/books",method = RequestMethod.GET)
    public String queryBooks(Model model){
        try {
            PageHelper.startPage(1, 4);
            List<Book> bookList = bookService.queryBooks();
            PageInfo<Book> pageInfo = new PageInfo<>(bookList);
            model.addAttribute("book_list", bookList);
            model.addAttribute("page_info", pageInfo);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "client/index";
    }

}
