package com.icepoint.framework.web.system.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icepoint.framework.web.core.support.StdEntityService;
import com.icepoint.framework.web.system.entity.TableMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * (TableMetadata)表服务接口
 *
 * @author makejava
 * @since 2021-06-01 16:38:09
 */
public interface TableMetadataService extends StdEntityService<TableMetadata, Long> {

    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return TableMetadata
     * @author Juyss
     */
    TableMetadata findOne(Long id);

    /**
     * 分页查询
     *
     * @param entity   属性用于构造条件查询
     * @param pageable 分页参数
     * @return Page<TableMetadata>
     * @author Juyss
     */
    Page<TableMetadata> page(TableMetadata entity, Pageable pageable);

    /**
     * 添加
     *
     * @param entity 数据对象
     * @return Boolean
     * @author Juyss
     */
    Boolean insert(TableMetadata entity);

    /**
     * 更新
     *
     * @param entity 数据对象
     * @return Boolean
     * @author Juyss
     */
    Boolean update(TableMetadata entity);

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return Integer
     * @author Juyss
     */
    Integer delete(List<Long> ids);


    List<TableMetadata> findByProjectId(Long id);

    Optional<TableMetadata> findByResourceId(Long resourceId);

    /**
     * 上传pdm 并解析
     */
    Boolean upLoadPdm(MultipartFile file);


    /**
     * 根据条件查询实体
     *
     * @param queryWrapper 条件
     */
    TableMetadata selectTable(LambdaQueryWrapper<TableMetadata> queryWrapper);
}
