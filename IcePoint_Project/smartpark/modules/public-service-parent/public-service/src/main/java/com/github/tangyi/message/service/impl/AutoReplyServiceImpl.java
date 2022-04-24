package com.github.tangyi.message.service.impl;

import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.message.entity.AutoReply;
import com.github.tangyi.message.mapper.AutoReplyMapper;
import com.github.tangyi.message.service.AutoReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName AutoReplyServiceImpl
 * @description
 * @since 2021-05-19 10:41
 */
@Service
public class AutoReplyServiceImpl implements AutoReplyService {

    @Autowired
    private AutoReplyMapper mapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AutoReply queryById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public PageResult queryAll(Integer pageNum, String name, Integer pageSize, String sort, String order) {
        return PageUtils.query(pageNum, pageSize, 10, () ->mapper.selectAll(name,sort,order));
    }

    /**
     * 新增数据
     *
     * @param autoReply 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(AutoReply autoReply) {
        return mapper.insert(autoReply);
    }

    /**
     * 修改数据
     *
     * @param autoReply 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AutoReply autoReply) {
        return mapper.updateByPrimaryKeySelective(autoReply);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        boolean flag = false;
        int i = mapper.deleteByPrimaryKey(id);
        if (i == 1){
            flag = true;
        }
        return flag;
    }

    /**
     * 通过id批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteByIds(List<Long> ids) {
        boolean flag = false;
        int i = mapper.deleteByIds(ids);
        if (i == ids.size()){
            flag = true;
        }
        return flag;
    }
}
