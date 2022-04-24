package com.icepoint.framework.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icepoint.framework.web.core.support.StdEntityServiceImpl;
import com.icepoint.framework.web.system.dao.FieldMetadataMapper;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.service.FieldMetadataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * (FieldMetadata)表服务实现类
 *
 * @author ck
 * @since 2021-05-19 14:26:08
 */
@Service("sysFieldService")
public class FieldMetadataServiceImpl
        extends StdEntityServiceImpl<FieldMetadata, Long>
        implements FieldMetadataService {

    @Resource
    private FieldMetadataMapper fieldMetadataMapper;

    /**
     * 根据id查询
     *
     * @param id 主键id
     * @return 数据对象
     */
    @Override
    public FieldMetadata queryById(Long id) {
        Optional<FieldMetadata> optional = fieldMetadataMapper.findById(id);
        return optional.orElse(null);
    }

    /**
     * 分页
     *
     * @param tabId    表id
     * @param pageable 分页参数
     * @return 分页数据
     */
    @Override
    public Page<FieldMetadata> pageList(Long tabId, Pageable pageable) {
        LambdaQueryWrapper<FieldMetadata> queryWrapper = new LambdaQueryWrapper<>();
        if (!ObjectUtils.isEmpty(tabId)) {
            queryWrapper.eq(FieldMetadata::getTableId, tabId);
        }
        return fieldMetadataMapper.findAll(queryWrapper, pageable);
    }

    /**
     * 新增
     *
     * @param sysField 数据对象
     * @return 是否添加成功
     */
    @Override
    public boolean saveSysFiled(FieldMetadata sysField) {
        return fieldMetadataMapper.insert(sysField) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids id数组
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer deleteSysField(List<Long> ids) {
        return fieldMetadataMapper.deleteInBatchIds(ids);
    }

    /**
     * 更新
     *
     * @param sysField 数据封装
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean updateSysField(FieldMetadata sysField) {
        return fieldMetadataMapper.update(sysField) == 1;
    }

    @Override
    public List<FieldMetadata> list(Wrapper<FieldMetadata> queryWrapper) {
        List<FieldMetadata> all = fieldMetadataMapper.findAll(queryWrapper);
        if (ObjectUtils.isEmpty(all)) {
            throw new IllegalStateException("表下没有字段");
        }
        return all;
    }

    @Override
    public List<FieldMetadata> findByTableId(Long tableId) {
        LambdaQueryWrapper<FieldMetadata> LambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper.eq(FieldMetadata::getTableId, tableId);
        List<FieldMetadata> all = fieldMetadataMapper.findAll(LambdaQueryWrapper);
        return all;
    }
}
