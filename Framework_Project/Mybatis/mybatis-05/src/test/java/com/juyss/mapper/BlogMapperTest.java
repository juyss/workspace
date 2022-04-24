package com.juyss.mapper;

import com.juyss.pojo.Blog;
import com.juyss.util.IdUtils;
import com.juyss.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BlogMapperTest
 * @Desc: BlogMapper测试类
 * @package com.juyss.mapper
 * @project Mybatis
 * @date 2020/8/28 16:23
 */
public class BlogMapperTest {

    @Test
    public void insertTest() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            for (int i = 0; i < 10; i++) {
                String uuid = IdUtils.getUUID();
                Blog blog = new Blog(uuid, "标题0" + i, "作者0" + i, new Date(1610056789461L), i * 14 + 4598);
                System.out.println(blog);
                mapper.insert(blog);
                System.out.println("第" + i + "条数据插入成功");
            }
            sqlSession.commit();
            System.out.println("事务提交成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }

    @Test
    public void queryBlogByInfoTest(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("views", 68768);
            map.put("title", "标题04");
            map.put("author", "作者07");
            Blog blog = mapper.queryBlogByInfo(map);
            System.out.println(blog);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}
