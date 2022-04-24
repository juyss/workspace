package com.icepoint.framework.code.sysgroup.service;

import com.icepoint.framework.code.sysgroup.entity.SysGroup;
import com.icepoint.framework.data.domain.TreeNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 分组表(SysGroup)表服务接口
 *
 * @author makejava
 * @since 2021-06-05 11:04:10
 */
public interface SysGroupService{

    /**
     * 创建资源
     * @param sysGroup 数据封装
     * @return 是否创建成功
     */
    boolean save(SysGroup sysGroup);

    /**
     * 更新
     * @author Juyss
     * @param sysGroup 对象封装
     * @return boolean
     */
    boolean update(SysGroup sysGroup);

    /**
     * 根据主键逻辑删除
     * @author Juyss
     * @param ids 主键集合
     * @return Integer
     */
    Integer delete(List<Long> ids);

    /**
     * 根据主键查询单条数据
     * @author Juyss
     * @param id 主键
     * @return SysProject
     */
    SysGroup findOne(Long id);

    /**
     * 条件查询并分页
     * @author Juyss
     * @param columnMap 条件
     * @param pageable 分页参数
     * @return Page<SysProject>
     */
    Page<SysGroup> findAll(Map<String, Object> columnMap, Pageable pageable);

    /**
     * 获取树形结构
     * @author Juyss
     * @return List<TreeNode<SysGroup>>
     */
    List<TreeNode<SysGroup>> getTreeList(Long rootId);

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
     * 修改分组文件
     * @param file 函数分组文件
     * @param id 用户id
     */
    boolean updateDescription(MultipartFile file, Long id);
}
