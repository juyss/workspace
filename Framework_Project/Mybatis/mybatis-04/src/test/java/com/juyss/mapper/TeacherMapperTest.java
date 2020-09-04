package com.juyss.mapper;

import com.juyss.pojo.Student;
import com.juyss.pojo.Teacher;
import com.juyss.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: TeacherMapperTest
 * @Desc:
 * @package com.juyss.mapper
 * @project Mybatis
 * @date 2020/8/28 14:13
 */
public class TeacherMapperTest {

    @Test
    public void getTeacherByIdTest() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            List<Teacher> teachers = sqlSession.getMapper(TeacherMapper.class).getTeacherList();
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }
}
