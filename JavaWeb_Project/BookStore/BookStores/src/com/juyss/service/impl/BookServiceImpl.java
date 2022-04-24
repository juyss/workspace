package com.juyss.service.impl;

import com.juyss.dao.BookDao;
import com.juyss.dao.impl.BookDaoImpl;
import com.juyss.pojo.Book;
import com.juyss.pojo.Page;
import com.juyss.service.BookService;
import com.juyss.util.MyDbutils;

import java.sql.Connection;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookServiceImpl
 * @Desc: 针对于Book的service实现
 * @package com.juyss.service.impl
 * @project BookStore
 * @date 2020/8/11 20:11
 */
public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();

    /**
     * 添加书籍
     *
     * @param book 要添加的书籍对象
     * @return 是否添加成功
     */
    @Override
    public Boolean addBook(Book book) {
        boolean flag = false;
        Connection conn = null;
        try {
            conn = MyDbutils.getConnection();
            int update = bookDao.addBook(conn, book);
            if (update == 1) {
                System.out.println("添加书籍成功");
                flag = true;
            } else {
                System.out.println("添加书籍失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDbutils.closeConnection(conn);
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
    public Boolean deleteBookById(int id) {
        boolean flag = false;
        Connection conn = null;
        try {
            conn = MyDbutils.getConnection();
            int update = bookDao.deleteBookById(conn, id);
            if (update == 1) {
                System.out.println("删除书籍成功");
                flag = true;
            } else {
                System.out.println("删除书籍失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDbutils.closeConnection(conn);
        }
        return flag;
    }

    /**
     * 修改书籍信息
     *
     * @param book 要修改的书籍对象
     * @return 是否修改成功
     */
    @Override
    public Boolean update(Book book) {
        boolean flag = false;
        Connection conn = null;
        try {
            conn = MyDbutils.getConnection();
            int update = bookDao.update(conn, book);
            if (update == 1) {
                System.out.println("修改书籍信息成功");
                flag = true;
            } else {
                System.out.println("修改书籍信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDbutils.closeConnection(conn);
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
    public Book queryBookById(int id) {
        Connection conn = null;
        Book book = null;
        try {
            conn = MyDbutils.getConnection();
            book = bookDao.queryBookById(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDbutils.closeConnection(conn);
        }
        return book;
    }

    /**
     * 查询所有书籍信息
     *
     * @return 查询到的书籍集合
     */
    @Override
    public List<Book> queryBooks() {
        Connection conn = null;
        List<Book> books = null;
        try {
            conn = MyDbutils.getConnection();
            books = bookDao.queryBooks(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDbutils.closeConnection(conn);
        }
        return books;
    }

    /**
     * 创建Page对象,对属性进行赋值
     *
     * @param pageNo 当前页码
     * @param pageSize 页面大小
     * @return Page对象
     */
    @Override
    public Page<Book> pageBooks(int pageNo, int pageSize) {
        Connection conn = null;
        Page<Book> bookPage = null;
        try {
            conn = MyDbutils.getConnection();
            bookPage = new Page<>();

            //设置page对象的当前页码和页面大小
            bookPage.setPageSize(pageSize);

            //设置page对象的数据条目总数
            List<Book> books = bookDao.queryBooks(conn);
            int pageTotalCount = books.size();
            bookPage.setPageTotalCount(pageTotalCount);

            //设置总分页数
            int pageTotal = (pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : pageTotalCount / pageSize + 1);
            bookPage.setPageTotal(pageTotal);

            //判断页码边境
            if (pageNo < 1) {
                pageNo = 1;
            }
            if (pageNo > pageTotal) {
                pageNo = pageTotal;
            }
            bookPage.setPageNo(pageNo);

            //分页显示的数据
            int begin = (pageNo - 1) * pageSize;
            if (begin < pageTotalCount) {
                List<Book> bookList = bookDao.queryPageBooks(conn, begin, pageSize);
                bookPage.setItems(bookList);
            } else {
                throw new RuntimeException("起始条目超出氛围!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            MyDbutils.closeConnection(conn);
        }
        return bookPage;
    }

    /**
     * 获取符合价格区间的Page对象
     *
     * @param pageNo   当前页码
     * @param pageSize 页面大小
     * @param min      最低价格
     * @param max      最高价格
     * @return Page对象
     */
    @Override
    public Page<Book> pageBooksByPrice(int pageNo, int pageSize, int min, int max) {
        Connection conn = null;
        Page<Book> bookPage = null;
        try {
            conn = MyDbutils.getConnection();
            bookPage = new Page<>();

            //设置页面大小
            bookPage.setPageSize(pageSize);

            //设置总数据条目数
            List<Book> books = bookDao.queryBooksByPrice(conn, min, max);
            int pageTotalCount = books.size();
            bookPage.setPageTotalCount(pageTotalCount);

            //设置总分页数
            int pageTotal = (pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : pageTotalCount / pageSize + 1);
            bookPage.setPageTotal(pageTotal);

            //判断页码边境
            if (pageNo < 1) {
                pageNo = 1;
            }
            if (pageNo > pageTotal) {
                pageNo = pageTotal;
            }
            bookPage.setPageNo(pageNo);

            //分页显示的数据
            int begin = (pageNo - 1) * pageSize;
            if (begin < pageTotalCount) {
                List<Book> bookList = bookDao.queryPageBooksByPrice(conn, begin, pageSize, min, max);
                bookPage.setItems(bookList);
            } else {
                throw new RuntimeException("起始条目超出氛围!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            MyDbutils.closeConnection(conn);
        }
        return bookPage;
    }
}
