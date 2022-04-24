package com.DAO;

import com.util.MyDbutils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BaseDAO
 * @Desc:  提供对数据库的通用操作
 * @package com.DAO
 * @project JDBC_Example
 * @date 2020/7/4 19:57
 */
public class BaseDAO {

    private static QueryRunner qr = new QueryRunner();

    /**
     * 通用的`增删改`方法
     * @param params Object
     * @param sql String
     * @return int 受影响的行数
     * @throws SQLException
     */
    protected static int update(String sql,Object... params) throws Exception {
        Connection conn = MyDbutils.getConnection();
        int update = qr.update(conn, sql, params);
        return update;
    }

    /**
     * 通用的查询方法
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    protected static <T> List<T> query(Class<T> clazz,String sql,Object... params) throws Exception {

        Connection conn = MyDbutils.getConnection();
        BeanListHandler<T> handler = new BeanListHandler<>(clazz);

        List<T> list = qr.query(conn, sql, handler, params);

        return list;
    }


}
