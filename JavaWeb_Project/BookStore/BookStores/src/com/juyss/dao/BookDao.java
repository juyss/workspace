package com.juyss.dao;

import com.juyss.pojo.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookDao
 * @Desc: 图书数据库访问接口
 * @package com.juyss.dao
 * @project BookStore
 * @date 2020/8/11 15:28
 */
public interface BookDao {

    /**
     * 添加图书
     *
     * @param book 需要添加的图书对象
     * @return 添加成功的图书数量
     */
    int addBook(Connection conn, Book book) throws SQLException;

    /**
     * 根据id删除图书
     *
     * @param id 图书id
     * @return 删除成功的图书数量
     */
    int deleteBookById(Connection conn, int id) throws SQLException;

    /**
     * 更新图书信息
     *
     * @param book 要更新的图书信息
     * @return 更新成功的图书数量
     */
    int update(Connection conn, Book book) throws SQLException;

    /**
     * 根据id查询指定图书
     *
     * @param id 要查询的图书id
     * @return 查询到的图书对象
     */
    Book queryBookById(Connection conn, int id) throws SQLException;

    /**
     * 查询所有图书
     *
     * @return 图书集合
     */
    List<Book> queryBooks(Connection conn) throws SQLException;

    /**
     * 查询分页后的图书列表
     * @param conn 数据库连接
     * @param begin 起始索引
     * @param pageSize 页面大小
     * @return 符合条件的图书集合
     * @throws SQLException SQL异常
     */
    List<Book> queryPageBooks(Connection conn,int begin , int pageSize) throws SQLException;

    /**
     * 查询符合价格区间,并且分页后的图书列表
     * @param conn 数据库连接
     * @param begin 起始索引
     * @param pageSize 页面大小
     * @param min 最低价格
     * @param max 最高价格
     * @return 符合条件的图书集合
     */
    List<Book> queryPageBooksByPrice(Connection conn,int begin, int pageSize,int min,int max) throws SQLException;

    /**
     * 查询符合价格区间条件的所有图书数
     * @param conn 数据库连接
     * @param min 最低价格
     * @param max 最高价格
     * @return 符合条件的图书列表
     */
    List<Book> queryBooksByPrice(Connection conn,int min,int max) throws SQLException;
}
