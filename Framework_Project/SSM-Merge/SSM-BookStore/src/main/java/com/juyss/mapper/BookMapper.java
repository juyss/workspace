package com.juyss.mapper;

import com.juyss.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookMapper
 * @Desc: book表的数据库操作接口
 * @package com.juyss.mapper
 * @project SSM-Merge
 * @date 2020/9/19 17:08
 */
public interface BookMapper {

    /**
     * 添加图书
     *
     * @param book 需要添加的图书对象
     * @return 添加成功的图书数量
     */
    int addBook(Book book) throws SQLException;

    /**
     * 根据id删除图书
     *
     * @param id 图书id
     * @return 删除成功的图书数量
     */
    int deleteBookById(Integer id) throws SQLException;

    /**
     * 更新图书信息
     *
     * @param book 要更新的图书信息
     * @return 更新成功的图书数量
     */
    int update(Book book) throws SQLException;

    /**
     * 根据id查询指定图书
     *
     * @param id 要查询的图书id
     * @return 查询到的图书对象
     */
    Book queryBookById(Integer id) throws SQLException;

    /**
     * 查询所有图书
     *
     * @return 图书集合
     */
    List<Book> queryBooks() throws SQLException;

    /**
     * 查询符合价格区间条件的所有图书数
     *
     * @param min 最低价格
     * @param max 最高价格
     * @return 符合条件的图书列表
     */
    List<Book> queryBooksByPrice(@Param("min") Integer min, @Param("max") Integer max) throws SQLException;

}
