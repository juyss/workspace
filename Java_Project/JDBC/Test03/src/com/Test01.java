package com;

import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Test01
 * @Desc:  测试对数据表进行操作
 * @package com
 * @project JDBC
 * @date 2020/6/2 21:51
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtils.getConnection();

//        String sql = "insert into person (`id`,`name`, `age`,`gender`,`birthday`) values (null,?,?,?,?)";
        String sql = "delete from `person` where `name`=?";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, "Dell");
//        pst.setInt(2, 20);
//        pst.setString(3, "男");
//        pst.setDate(4, new Date(System.currentTimeMillis()));

        pst.execute();
        JDBCUtils.closeResource(pst, conn);
    }
}
