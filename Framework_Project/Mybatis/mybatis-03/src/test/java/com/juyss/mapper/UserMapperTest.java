package com.juyss.mapper;

import com.juyss.pojo.Student;
import com.juyss.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapperTest
 * @Desc: UserMapper测试类
 * @package com.juyss.mapper
 * @project Mybatis
 * @date 2020/8/27 14:26
 */
public class UserMapperTest {

    /**
     * 多对一查询
     */
    @Test
    public void getStudentListTest01() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> list = mapper.getStudentList();
            for (Student student : list) {
                System.out.println(student);
            }
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }

}
