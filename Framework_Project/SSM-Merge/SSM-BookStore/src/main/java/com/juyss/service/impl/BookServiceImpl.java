package com.juyss.service.impl;

import com.juyss.mapper.BookMapper;
import com.juyss.pojo.Book;
import com.juyss.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookServiceImpl
 * @Desc: BookService接口实现类
 * @package com.juyss.service.impl
 * @project SSM-Merge
 * @date 2020/9/19 18:37
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;

    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    /**
     * 添加书籍
     *
     * @param book 要添加的书籍对象
     * @return 是否添加成功
     */
    @Override
    public Boolean addBook(Book book) throws SQLException {
        boolean flag = false;
        int i = bookMapper.addBook(book);
        if (i == 1) {
            flag=true;
        }
        return flag;
    }

    /**
     * 根据id删除书籍
     *
     * @param id 需要删除的书籍id
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteBookById(int id) throws SQLException {
        boolean flag = false;
        int i = bookMapper.deleteBookById(id);
        if (i == 1) {
            flag=true;
        }
        return flag;
    }

    /**
     * 更新书籍信息
     *
     * @param book 要修改的书籍对象
     * @return 是否修改成功
     */
    @Override
    public Boolean update(Book book) throws SQLException {
        boolean flag = false;
        int i = bookMapper.update(book);
        if (i == 1) {
            flag=true;
        }
        return flag;
    }

    /**
     * 根据id查询书籍信息
     *
     * @param id 要查询的书籍id
     * @return 查询到的书籍对象
     */
    @Override
    public Book queryBookById(int id) throws SQLException {
        return bookMapper.queryBookById(id);
    }

    /**
     * 查询所有书籍信息
     *
     * @return 查询到的书籍集合
     */
    @Override
    public List<Book> queryBooks() throws SQLException {
        return bookMapper.queryBooks();
    }

    /**
     * 查询符合条件的书籍列表
     *
     * @param min 最低价格
     * @param max 最高价格
     * @return 符合条件的Book集合
     */
    @Override
    public List<Book> queryBooksByPrice(int min, int max) throws SQLException {
        return bookMapper.queryBooksByPrice(min,max);
    }
}
