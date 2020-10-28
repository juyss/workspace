package com.juyss.service;

import com.juyss.pojo.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookService
 * @Desc:
 * @package com.juyss.service
 * @project SSM-Merge
 * @date 2020/9/19 17:59
 */
public interface BookService {

    /**
     * 添加书籍
     *
     * @param book 要添加的书籍对象
     * @return 是否添加成功
     */
    Boolean addBook(Book book) throws SQLException;

    /**
     * 根据id删除书籍
     *
     * @param id 需要删除的书籍id
     * @return 是否删除成功
     */
    Boolean deleteBookById(int id) throws SQLException;

    /**
     * 更新书籍信息
     *
     * @param book 要修改的书籍对象
     * @return 是否修改成功
     */
    Boolean update(Book book) throws SQLException;

    /**
     * 根据id查询书籍信息
     *
     * @param id 要查询的书籍id
     * @return 查询到的书籍对象
     */
    Book queryBookById(int id) throws SQLException;

    /**
     * 查询所有书籍信息
     *
     * @return 查询到的书籍集合
     */
    List<Book> queryBooks() throws SQLException;

    /**
     * 查询符合条件的书籍列表
     * @param min 最低价格
     * @param max 最高价格
     * @return 符合条件的Book集合
     */
    List<Book> queryBooksByPrice(int min,int max) throws SQLException;

}
