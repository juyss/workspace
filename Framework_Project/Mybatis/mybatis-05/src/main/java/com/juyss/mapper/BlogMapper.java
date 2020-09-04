package com.juyss.mapper;

import com.juyss.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BlogMapper
 * @Desc: blog表的操作接口
 * @package com.juyss.mapper
 * @project Mybatis
 * @date 2020/8/28 16:15
 */
public interface BlogMapper {

    /**
     * 获取所有的Blog
     * @return List<Blog>
     */
    List<Blog> getBlogList();

    /**
     * 按标题查询
     * @param Title String 标题
     * @return Blog
     */
    Blog getBlogByTitle(String Title);

    /**
     * 根据map中的条件查询结果
     * @param map 条件键值对
     * @return Blog 查询到的结果
     */
    Blog queryBlogByInfo(Map map);

    /**
     * 插入一条数据
     * @param blog Blog 要插入的数据封装对象
     * @return int 受影响的行数
     */
    int insert(Blog blog);
}
