package com.shme.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MybatisUtils
 * @Desc: Mybatis工具类
 * @package com.shme.util
 * @project smbms
 * @date 2020/8/29 16:34
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String xmlPath = "mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream(xmlPath);
            System.out.println("文件流-->" + is);
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            System.out.println("创建者-->builder-->"+builder);
            sqlSessionFactory = builder.build(is);
            System.out.println("工厂sqlSessionFactory-->" + sqlSessionFactory);
        } catch (IOException e) {
            System.out.println("Utils报错");
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession实例-->"+sqlSession);
        return sqlSession;
    }
}
