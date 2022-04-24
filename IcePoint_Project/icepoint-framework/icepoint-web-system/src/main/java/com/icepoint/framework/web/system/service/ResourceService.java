package com.icepoint.framework.web.system.service;

import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.core.support.StdEntityService;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.entity.TableMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 资源表(Resource)表服务接口
 *
 * @author makejava
 * @since 2021-05-27 10:41:25
 */
public interface ResourceService extends StdEntityService<Resource, Long> {

    /**
     * 更新
     * @author Juyss
     * @param genericResource 对象封装
     * @return boolean
     */
    boolean update(Resource genericResource);

    /**
     * 根据主键逻辑删除
     * @author Juyss
     * @param ids 主键集合
     * @return boolean
     */
    boolean delete(List<Long> ids);

    /**
     * 根据主键查询单条数据
     * @author Juyss
     * @param id 主键
     * @return SysProject
     */
    Resource findOne(Long id);

    /**
     * 根据ProjectId查询单条数据
     * @author Juyss
     * @param projectId 项目ID
     * @return SysProject
     */
    List<Resource> findOneByProjId(Long projectId);

    /**
     * 条件查询并分页
     * @author Juyss
     * @param columnMap 条件
     * @param pageable 分页参数
     * @return Page<SysProject>
     */
    Page<Resource> findAll(Map<String, Object> columnMap, Pageable pageable);

    /**
     * 获取树形结构
     * @author Juyss
     * @return List<TreeNode<Resource>>
     */
    List<TreeNode<Resource>> getTreeList(Long rootId);

    /**
     * 移动节点，命令（置顶 :top  置底 :end 移动 :move）
     * @author Juyss
     * @param parentId 父节点ID
     * @param thisId 此节点ID
     * @param anotherId 另一个需要修改的节点ID
     * @param command 移动命令
     * @return Boolean
     */
    Boolean updateIdxById(Long parentId, Long thisId, Long anotherId, String command);

    /**
     * 根据资源编码查询表元数据和字段元数据
     * @author Juyss
     * @param id 资源编码
     * @return FieldMetadata
     */
    TableMetadata getInfoByResourceId(Long id);
}
