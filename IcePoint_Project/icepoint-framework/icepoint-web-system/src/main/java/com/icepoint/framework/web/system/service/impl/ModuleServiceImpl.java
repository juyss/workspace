package com.icepoint.framework.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icepoint.framework.web.system.dao.ModuleMapper;
import com.icepoint.framework.web.system.entity.Module;
import com.icepoint.framework.web.system.service.ModuleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-21 17:07
 */
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleMapper mapper;

    /**
     * 根据id查询
     *
     * @param id 主键id
     * @return 数据对象
     */
    @Override
    public Module queryById(Long id) {
        Optional<Module> optional = mapper.findById(id);
        return optional.orElse(null);
    }

    /**
     * 分页
     *
     * @param projectId    工程id
     * @param pageable 分页参数
     * @return 分页数据
     */
    @Override
    public Page<Module> pageList(Long projectId, Pageable pageable) {
        LambdaQueryWrapper<Module> queryWrapper = new LambdaQueryWrapper<>();
        if (!ObjectUtils.isEmpty(projectId)) {
            queryWrapper.eq(Module::getProjectId, projectId);
        }
        return mapper.findAll(queryWrapper, pageable);
    }

    /**
     * 新增
     *
     * @param module 数据对象
     * @return 是否添加成功
     */
    @Override
    public boolean save(Module module) {
        return mapper.insert(module) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids id数组
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer delete(List<Long> ids) {
        return mapper.deleteInBatchIds(ids);
    }

    /**
     * 单个删除
     *
     * @param id 主键
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer deleteOne(Long id) {
        return mapper.deleteById(id);
    }

    /**
     * 更新
     *
     * @param module 数据封装
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean update(Module module) {
        return mapper.update(module) == 1;
    }
}
