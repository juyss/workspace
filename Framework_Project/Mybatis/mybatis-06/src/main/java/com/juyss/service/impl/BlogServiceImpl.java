package com.juyss.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juyss.mapper.BlogMapper;
import com.juyss.pojo.Blog;
import com.juyss.service.BlogService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BlogServiceImpl
 * @Desc:
 * @package com.juyss.service.impl
 * @project Mybatis
 * @date 2020/9/18 16:15
 */
public class BlogServiceImpl implements BlogService {

    private BlogMapper mapper;

    public BlogServiceImpl(SqlSession sqlSession) {
        this.mapper = sqlSession.getMapper(BlogMapper.class);
    }


    /**
     * 展示查询到的blog信息
     *
     * @return List 查询到的blog信息
     */
    @Override
    public List<Blog> showBlogs() {
        return mapper.getBlogList();
    }


}
