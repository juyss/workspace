package com.juyss.util;

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
 * @Desc:  Mybatis工具类包
 * @package com.juyss.util
 * @project Mybatis
 * @date 2020/8/25 18:02
 */
public class MybatisUtils {

    /**
     * 从SqlSessionFactory中可以获取SqlSession实例
     */
    private static SqlSessionFactory sqlSessionFactory;

    //获取sqlSessionFactory对象
    static{
        try {
            String resource = "Mybatis/mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取SqlSession对象
     * @return SqlSession实例
     */
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }


}
