package com.github.tangyi.message.service;

import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.message.entity.AutoReply;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName AutoReplyService
 * @description
 * @since  2021-05-19 10:34
 */
public interface AutoReplyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AutoReply queryById(Long id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    PageResult queryAll(Integer pageNum, String name, Integer pageSize, String sort, String order);

    /**
     * 新增数据
     *
     * @param autoReply 实例对象
     * @return 实例对象
     */
    int insert(AutoReply autoReply);

    /**
     * 修改数据
     *
     * @param autoReply 实例对象
     * @return 实例对象
     */
    int update(AutoReply autoReply);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过id批量删除
     * @param ids
     * @return
     */
    boolean deleteByIds(List<Long> ids);

}