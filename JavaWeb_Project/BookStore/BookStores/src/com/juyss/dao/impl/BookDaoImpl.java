package com.juyss.dao.impl;

import com.juyss.dao.BaseDao;
import com.juyss.dao.BookDao;
import com.juyss.pojo.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookDaoImpl
 * @Desc:
 * @package com.juyss.dao.impl
 * @project BookStore
 * @date 2020/8/11 17:25
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    /**
     * 添加图书
     *
     * @param book 需要添加的图书对象
     * @return 添加成功的图书数量
     */
    @Override
    public int addBook(Connection conn, Book book) throws SQLException {
        String sql = "insert into `t_book` (`name` , `author` , `price` , `sales` , `stock` , `img_path`) values (?,?,?,?,?,?)";
        return update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    /**
     * 根据id删除图书
     *
     * @param id 图书id
     * @return 删除成功的图书数量
     */
    @Override
    public int deleteBookById(Connection conn, int id) throws SQLException {
        String sql = "delete from `t_book` where `id` = ?";
        return update(conn, sql, id);
    }

    /**
     * 更新图书信息
     *
     * @param book 要更新的图书信息
     * @return 更新成功的图书数量
     */
    @Override
    public int update(Connection conn, Book book) throws SQLException {
        String sql = "update `t_book` set `name` = ?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where `id`=?";
        return update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(),book.getId());
    }

    /**
     * 根据id查询指定图书
     *
     * @param id 要查询的图书id
     * @return 查询到的图书对象
     */
    @Override
    public Book queryBookById(Connection conn, int id) throws SQLException {
        String sql = "select * from `t_book` where `id`=?";
        return query(Book.class, conn, sql, id);
    }

    /**
     * 查询所有图书
     *
     * @return 图书集合
     */
    @Override
    public List<Book> queryBooks(Connection conn) throws SQLException {
        String sql = "select * from `t_book`";
        return queryAll(Book.class, conn, sql);
    }

    /**
     * 查询分页内容
     *
     * @param begin 起始索引
     * @param pageSize 分页大小
     * @return 分页后的图书列表
     */
    @Override
    public List<Book> queryPageBooks(Connection conn,int begin, int pageSize) throws SQLException {
        String sql = "select * from `t_book` limit ?,?";
        return queryAll(Book.class, conn, sql, begin, pageSize);
    }

    /**
     * 查询符合价格区间,并且分页后的图书列表
     *
     * @param conn     数据库连接
     * @param begin    起始索引
     * @param pageSize 页面大小
     * @param min      最低价格
     * @param max      最高价格
     * @return 符合条件的图书集合
     */
    @Override
    public List<Book> queryPageBooksByPrice(Connection conn, int begin, int pageSize, int min, int max) throws SQLException {
        String sql = "select * from t_book where price between ? and ? order by price asc limit ?,?";
        return queryAll(Book.class, conn, sql, min, max, begin, pageSize);
    }

    /**
     * 查询符合价格区间条件的所有图书数
     *
     * @param conn 数据库连接
     * @param min  最低价格
     * @param max  最高价格
     * @return 符合条件的图书列表
     */
    @Override
    public List<Book> queryBooksByPrice(Connection conn, int min, int max) throws SQLException {
        String sql = "select * from t_book where price between ? and ?";
        return queryAll(Book.class, conn, sql, min, max);
    }
}
