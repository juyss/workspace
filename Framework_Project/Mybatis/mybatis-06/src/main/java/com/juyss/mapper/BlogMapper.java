package com.juyss.mapper;

import com.juyss.pojo.Blog;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BlogMapper
 * @Desc: blog表的数据库操作接口
 * @package com.juyss.mapper
 * @project Mybatis
 * @date 2020/9/18 16:09
 */
public interface BlogMapper {

    /**
     * 获取所有的Blog
     * @return List<Blog>
     */
    List<Blog> getBlogList();
}
