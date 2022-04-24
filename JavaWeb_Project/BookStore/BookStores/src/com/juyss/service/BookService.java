package com.juyss.service;

import com.juyss.pojo.Book;
import com.juyss.pojo.Page;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookService
 * @Desc:
 * @package com.juyss.service
 * @project BookStore
 * @date 2020/8/11 20:02
 */
public interface BookService {

    /**
     * 添加书籍
     *
     * @param book 要添加的书籍对象
     * @return 是否添加成功
     */
    Boolean addBook(Book book);

    /**
     * 根据id删除书籍
     *
     * @param id 需要删除的书籍id
     * @return 是否删除成功
     */
    Boolean deleteBookById(int id);

    /**
     * 更新书籍信息
     *
     * @param book 要修改的书籍对象
     * @return 是否修改成功
     */
    Boolean update(Book book);

    /**
     * 根据id查询书籍信息
     *
     * @param id 要查询的书籍id
     * @return 查询到的书籍对象
     */
    Book queryBookById(int id);

    /**
     * 查询所有书籍信息
     *
     * @return 查询到的书籍集合
     */
    List<Book> queryBooks();

    /**
     * 获取Page对象
     *
     * @param pageNo  当前页码
     * @param pageSize  页面大小
     * @return 封装好的Page对象
     */
    Page<Book> pageBooks(int pageNo, int pageSize);

    /**
     * 获取符合价格区间的Page对象
     *
     * @param pageNo 当前页码
     * @param pageSize 页面大小
     * @param min 最低价格
     * @param max 最高价格
     * @return
     */
    Page<Book> pageBooksByPrice(int pageNo, int pageSize,int min,int max) throws Exception;
}
