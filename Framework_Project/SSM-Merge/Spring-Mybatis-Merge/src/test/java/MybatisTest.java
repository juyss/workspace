import com.juyss.mapper.UserMapper;
import com.juyss.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MybatisTest
 * @Desc: 测试Mybatis基本功能(与Spring整合后运行会报错,正常)
 * @package PACKAGE_NAME
 * @project Spring-Mybatis-Merge
 * @date 2020/9/10 15:51
 */
public class MybatisTest {

    @Test
    public void Test01(){
        SqlSession sqlSession = null;
        try {
            InputStream is = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            sqlSession = sqlSessionFactory.openSession();

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = mapper.getUserList();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }

    }
}
