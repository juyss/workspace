package com.icepoint.framework.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.data.util.TreeUtils;
import com.icepoint.framework.web.core.support.StdEntityServiceImpl;
import com.icepoint.framework.web.system.dao.FieldMetadataMapper;
import com.icepoint.framework.web.system.dao.ResourceMapper;
import com.icepoint.framework.web.system.dao.TableMetadataMapper;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 资源表(Resource)表服务实现类
 *
 * @author makejava
 * @since 2021-05-27 10:41:26
 */
@Transactional(rollbackFor = Exception.class)
@Slf4j
@Service("genericResourceService")
public class ResourceServiceImpl extends StdEntityServiceImpl<Resource, Long> implements ResourceService {

    @javax.annotation.Resource
    private ResourceMapper mapper;

    @javax.annotation.Resource
    private TableMetadataMapper tableMetadataMapper;

    @javax.annotation.Resource
    private FieldMetadataMapper fieldMetadataMapper;

    /**
     * 创建资源
     *
     * @param entity 数据封装
     * @return 是否创建成功
     */
    @Override
    public Resource save(Resource entity) {
        Long parentId = entity.getParentId();
        //查询最大索引
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Resource::getParentId, parentId).orderByAsc(Resource::getIdx).last("limit 1");
        Optional<Resource> optional = mapper.findOne(wrapper);
        if (optional.isPresent()) {
            Resource resource = optional.get();
            int minIndex = resource.getIdx();
            entity.setIdx(minIndex - 1);
            mapper.insert(entity);

            Assert.notNull(entity.getId(), "添加失败");

        }
        return entity;
    }

    /**
     * 更新
     *
     * @param genericResource 对象封装
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean update(Resource genericResource) {
        int update = mapper.update(genericResource);
        return update == 1;
    }

    /**
     * 根据主键逻辑删除
     *
     * @param ids 主键集合
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean delete(List<Long> ids) {
        try {
            for (Long id : ids) {
                //现根据id查询
                LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Resource::getId, id);
                Optional<Resource> genericResourceOptional = mapper.findOne(wrapper);
                //如果存在数据，再进行逻辑删除
                if (genericResourceOptional.isPresent()) {

                    wrapper.clear();
                    //查询子节点
                    wrapper.eq(Resource::getParentId, id);
                    List<Resource> list = mapper.findAll(wrapper);
                    if (list.size() != 0) {
                        //删除当前节点的全部子节点
                        mapper.deleteInBatchIds(list.stream().map(Resource::getId).collect(Collectors.toList()));
                    }

                    //删除当前节点
                    Resource genericResource = genericResourceOptional.get();
                    assert genericResource.getId() != null;
                    int delete = mapper.deleteById(genericResource.getId());

                    return delete == 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据主键查询单条数据
     *
     * @param id 主键
     * @return SysProject
     * @author Juyss
     */
    @Override
    public Resource findOne(Long id) {
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Resource::getId, id);
        Optional<Resource> genericResource = mapper.findOne(wrapper);
        return genericResource.orElse(null);
    }

    /**
     * 根据ProjectId查询单条数据
     *
     * @param projectId 项目ID
     * @return SysProject
     * @author Juyss
     */
    @Override
    public List<Resource> findOneByProjId(Long projectId) {
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Resource::getProjectId, projectId);
        return mapper.findAll(wrapper);
    }


    /**
     * 条件查询并分页
     *
     * @param columnMap 条件
     * @param pageable  分页参数
     * @return Page<SysProject>
     * @author Juyss
     */
    @Override
    public Page<Resource> findAll(Map<String, Object> columnMap, Pageable pageable) {
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.allEq(columnMap).orderByAsc("idx");
        return mapper.findAll(wrapper, pageable);
    }

    /**
     * 获取树形结构
     *
     * @return List<TreeNode < Resource>>
     * @author Juyss
     */
    @Override
    public List<TreeNode<Resource>> getTreeList(Long rootId) {
        List<Resource> resourceList = mapper.findAll();
        List<Resource> list = resourceList.stream()
                .sorted(Comparator.comparing(Resource::getIdx))
                .collect(Collectors.toList());
        return TreeUtils.buildTreeStructure(list, rootId);
    }

    /**
     * 移动节点，命令（置顶 :top  置底 :end 移动 :move）
     *
     * @param parentId  父节点ID
     * @param thisId    此节点ID
     * @param anotherId 另一个需要修改的节点ID
     * @param command   移动命令
     * @return Boolean
     * @author Juyss
     */
    @Override
    public Boolean updateIdxById(Long parentId, Long thisId, Long anotherId, String command) {
        //如果命令移动命令为空，返回false
        if (ObjectUtils.isEmpty(command)) {
            return false;
        }

        //更新数量
        boolean flag = false;

        //置顶，设置此节点 idx=MIN(idx)-0.1
        if ("top".equalsIgnoreCase(command)) {

            //查询最小索引
            LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Resource::getParentId, parentId).orderByAsc(Resource::getIdx).last("limit 1");
            Optional<Resource> optional = mapper.findOne(wrapper);
            if (optional.isPresent()) {
                Resource genericResource = optional.get();

                //得到最小索引
                int minOrder = genericResource.getIdx();
                wrapper.clear();
                wrapper.eq(Resource::getId, thisId);
                Optional<Resource> thisOptional = mapper.findOne(wrapper);
                if (thisOptional.isPresent()) {
                    Resource resource = thisOptional.get();
                    //更新节点索引值
                    resource.setIdx(minOrder - 1);
                    int updated = mapper.update(resource);
                    flag = updated == 1;
                }
            }

        }

        //置底，设置此节点 idx=MAX(idx)+0.1
        if ("end".equalsIgnoreCase(command)) {
            //查询最大索引
            LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Resource::getParentId, parentId).orderByDesc(Resource::getIdx).last("limit 1");
            Optional<Resource> optional = mapper.findOne(wrapper);
            if (optional.isPresent()) {
                Resource genericResource = optional.get();

                //得到最大索引
                int maxOrder = genericResource.getIdx();
                wrapper.clear();
                wrapper.eq(Resource::getId, thisId);
                Optional<Resource> thisOptional = mapper.findOne(wrapper);
                if (thisOptional.isPresent()) {
                    Resource resource = thisOptional.get();
                    //更新节点索引值
                    resource.setIdx(maxOrder + 1);
                    int updated = mapper.update(resource);
                    flag = updated == 1;
                }
            }
        }

        //移动，将两个节点idx数值进行交换
        if ("move".equalsIgnoreCase(command)) {
            //先查询是否有数据
            Optional<Resource> thisOptional = mapper.findById(thisId);
            Optional<Resource> anotherOptional = mapper.findById(anotherId);

            if (thisOptional.isPresent() && anotherOptional.isPresent()) {
                //获取节点数据
                Resource thisNode = thisOptional.get();
                Resource anotherNode = anotherOptional.get();
                int thisOrder = thisNode.getIdx();
                int anotherOrder = anotherNode.getIdx();
                //order值调换
                thisNode.setIdx(anotherOrder);
                anotherNode.setIdx(thisOrder);
                //更新数据
                int thisUpdated = mapper.update(thisNode);
                int anotherUpdated = mapper.update(anotherNode);

                flag = thisUpdated + anotherUpdated == 2;
            }
        }

        return flag;
    }

    /**
     * 根据资源编码查询表元数据和字段元数据
     *
     * @param id 资源编码
     * @return FieldMetadata
     * @author Juyss
     */
    @Override
    public TableMetadata getInfoByResourceId(Long id) {
        LambdaQueryWrapper<Resource> resourceWrapper = Wrappers.lambdaQuery(Resource.class);
        resourceWrapper.eq(Resource::getId, id);
        //获取资源信息
        Resource resource = mapper.findOne(resourceWrapper).orElseThrow(() -> new IllegalArgumentException("对应资源不存在"));

        LambdaQueryWrapper<TableMetadata> tableWrapper = Wrappers.lambdaQuery(TableMetadata.class);
        tableWrapper.eq(TableMetadata::getResourceId, resource.getId());
        //获取表元数据信息
        TableMetadata tableMetadata = tableMetadataMapper.findOne(tableWrapper)
                .orElseThrow(() -> new IllegalArgumentException("对应表元数据不存在"));

        LambdaQueryWrapper<FieldMetadata> fieldWrapper = Wrappers.lambdaQuery(FieldMetadata.class);
        fieldWrapper.eq(FieldMetadata::getTableId, tableMetadata.getId());
        List<FieldMetadata> fieldList = fieldMetadataMapper.findAll(fieldWrapper);
        tableMetadata.setFieldMetadatas(fieldList);
        return tableMetadata;
    }
}
