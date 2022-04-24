package com.juyss.mapper;

import com.juyss.pojo.Book;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookMapper
 * @Desc: book表的数据库操作接口
 * @package com.juyss.mapper
 * @project SSM-Merge
 * @date 2020/9/13 16:02
 */
public interface BookMapper {

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
     * @return 受影响的行数
     */
    int insertBook(Book book);

    /**
     * 更新一条数据
     * @param book 要更新的Book对象实例
     * @return 受影响的行数
     */
    int updateBook(Book book);

    /**
     * 删除一条数据
     * @param id 要删除的数据的id
     * @return 受影响的行数
     */
    int deleteBookById(Integer id);
}
