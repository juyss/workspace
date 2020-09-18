package com.juyss.service;

import com.juyss.pojo.Blog;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BlogService
 * @Desc:
 * @package com.juyss.service
 * @project Mybatis
 * @date 2020/9/18 16:10
 */
public interface BlogService {

    /**
     * 展示查询到的blog信息
     *
     * @return List 查询到的blog信息
     */
    List<Blog> showBlogs();
}
