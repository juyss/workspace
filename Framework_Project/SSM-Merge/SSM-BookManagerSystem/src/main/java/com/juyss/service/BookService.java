package com.juyss.service;

import com.juyss.pojo.Book;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookService
 * @Desc: 业务层接口
 * @package com.juyss.service
 * @project SSM-Merge
 * @date 2020/9/13 17:36
 */
public interface BookService {

    /**
     * 获取Book表所有数据
     * @return Book数据集合
     */
    List<Book> getBookList();

    /**
     * 获取指定id的Book
     * @param id 要获取的Book
     * @return 查询到的Book数据
     */
    Book getBookById(Integer id);

    /**
     * 插入一条数据
     * @param book 要插入的Book对象实例
     * @return Boolean 操作是否成功
     */
    Boolean insertBook(Book book);

    /**
     * 更新一条数据
     * @param book 要更新的Book对象实例
     * @return Boolean 操作是否成功
     */
    Boolean updateBook(Book book);

    /**
     * 删除一条数据
     * @param id 要删除的数据的id
     * @return Boolean 操作是否成功
     */
    Boolean deleteBookById(Integer id);
}
