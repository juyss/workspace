package com.juyss;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class DataApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            System.out.println("获得连接:"+conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null){
                    conn.close();
                    System.out.println("连接关闭");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
