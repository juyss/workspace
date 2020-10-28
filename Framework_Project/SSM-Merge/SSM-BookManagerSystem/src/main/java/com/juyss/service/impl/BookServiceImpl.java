package com.juyss.service.impl;

import com.juyss.mapper.BookMapper;
import com.juyss.pojo.Book;
import com.juyss.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookServiceImpl
 * @Desc: 业务层接口实现类
 * @package com.juyss.service.impl
 * @project SSM-Merge
 * @date 2020/9/13 17:37
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;


    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public BookServiceImpl() {
        System.out.println("调用了BookServiceImpl类无参构造器");
    }

    /**
     * 获取Book表所有数据
     *
     * @return Book数据集合
     */
    @Override
    public List<Book> getBookList() {
        return bookMapper.getBookList();
    }

    /**
     * 获取指定id的Book
     *
     * @param id 要获取的Book
     * @return 查询到的Book数据
     */
    @Override
    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }

    /**
     * 插入一条数据
     *
     * @param book 要插入的Book对象实例
     * @return Boolean 操作是否成功
     */
    @Override
    public Boolean insertBook(Book book) {
        boolean flag = false;
        if (bookMapper.insertBook(book)==1){
            flag=true;
        }
        return flag;
    }

    /**
     * 更新一条数据
     *
     * @param book 要更新的Book对象实例
     * @return Boolean 操作是否成功
     */
    @Override
    public Boolean updateBook(Book book) {
        boolean flag = false;
        if (bookMapper.updateBook(book)==1){
            flag=true;
        }
        return flag;
    }

    /**
     * 删除一条数据
     *
     * @param id 要删除的数据的id
     * @return Boolean 操作是否成功
     */
    @Override
    public Boolean deleteBookById(Integer id) {
        boolean flag = false;
        if (bookMapper.deleteBookById(id)==1){
            flag=true;
        }
        return flag;
    }
}
