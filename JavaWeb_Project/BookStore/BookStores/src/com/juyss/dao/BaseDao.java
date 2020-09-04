package com.juyss.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BaseDao
 * @Desc: DAO(数据库访问对象基类)
 * @package com.juyss.dao
 * @project BookStore
 * @date 2020/8/7 16:41
 */
public abstract class BaseDao {

    private QueryRunner qr = new QueryRunner();

    /**
     * 更新一条数据
     *
     * @param conn Connection
     * @param sql  String
     * @param args Object...
     * @return int 受影响的行数
     */
    public int update(Connection conn, String sql, Object... args) throws SQLException {
        int update = qr.update(conn, sql, args);
        return update;
    }

    /**
     * 查询一条数据
     *
     * @param type 返回的类型
     * @param conn Connection
     * @param sql  String
     * @param args Object...
     * @param <T>  返回类型的泛型
     * @return T 返回泛型对象
     */
    public <T> T query(Class<T> type, Connection conn, String sql, Object... args) throws SQLException {
        BeanHandler<T> handler = new BeanHandler<>(type);
        T t = qr.query(conn, sql, handler, args);
        return t;
    }

    /**
     * 查询多条数据
     *
     * @param type 返回的类型
     * @param conn Connection
     * @param sql  String
     * @param args Object...
     * @param <T>  返回类型的泛型
     * @return List<T> 返回的对象集合
     */
    public <T> List<T> queryAll(Class<T> type, Connection conn, String sql, Object... args) throws SQLException {
        BeanListHandler<T> handler = new BeanListHandler<>(type);
        List<T> list = qr.query(conn, sql, handler, args);
        return list;
    }

    /**
     * 查询特殊值
     *
     * @param conn Connection
     * @param sql  String
     * @param args Object...
     * @return Object 返回的数据对象
     */
    public Object getValue(Connection conn, String sql, Object... args) throws SQLException {
        ScalarHandler handler = new ScalarHandler();
        Object o = qr.query(conn, sql, handler, args);
        return o;
    }
}
