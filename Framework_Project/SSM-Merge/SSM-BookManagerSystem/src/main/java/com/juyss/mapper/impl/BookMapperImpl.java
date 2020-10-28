package com.juyss.mapper.impl;

import com.juyss.mapper.BookMapper;
import com.juyss.pojo.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookMapperImpl
 * @Desc: BookMapper实现类
 * @package com.juyss.mapper.impl
 * @project SSM-Merge
 * @date 2020/9/13 18:41
 */
@Repository("bookMapper")
public class BookMapperImpl implements BookMapper {


    private SqlSession sqlSession;

    //自动注入sqlSession
    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public BookMapperImpl() {
        System.out.println("调用了BookMapperImpl类无参构造器");
    }

    /**
     * 获取Book表所有数据
     *
     * @return Book数据集合
     */
    @Override
    public List<Book> getBookList() {
        return sqlSession.getMapper(BookMapper.class).getBookList();
    }

    /**
     * 获取指定id的Book
     *
     * @param id 要获取的Book
     * @return 查询到的Book数据
     */
    @Override
    public Book getBookById(@Param("bookId") Integer id) {
        return sqlSession.getMapper(BookMapper.class).getBookById(id);
    }

    /**
     * 插入一条数据
     *
     * @param book 要插入的Book对象实例
     * @return 受影响的行数
     */
    @Override
    public int insertBook(Book book) {
        return sqlSession.getMapper(BookMapper.class).insertBook(book);
    }

    /**
     * 更新一条数据
     *
     * @param book 要更新的Book对象实例
     * @return 受影响的行数
     */
    @Override
    public int updateBook(Book book) {
        return sqlSession.getMapper(BookMapper.class).updateBook(book);
    }

    /**
     * 删除一条数据
     *
     * @param id 要删除的数据的id
     * @return 受影响的行数
     */
    @Override
    public int deleteBookById(@Param("bookId") Integer id) {
        return sqlSession.getMapper(BookMapper.class).deleteBookById(id);
    }
}
