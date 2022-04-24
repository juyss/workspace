package com.github.tangyi.introduction.service;

import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.introduction.entity.GeneralIntroduction;

import java.util.List;

/**
 * @ClassName: GeneralIntroductionService
 * @Desc: 通用简介
 * @Author Juyss
 * @Date: 2021-04-16 10:35
 * @Version 1.0
 */
public interface GeneralIntroductionService {

    /**
     * 选择插入
     * @param generalIntroduction 数据封装对象
     * @return 是否添加成功
     */
    Boolean insert(GeneralIntroduction generalIntroduction);

    /**
     * 逻辑删除一条数据
     * @param id 要删除的数据的ID
     * @return 是否删除成功
     */
    Boolean delete(Long id);

    /**
     * 选择更新
     * @param generalIntroduction 数据封装对象
     * @return 是否更新成功
     */
    Boolean update(GeneralIntroduction generalIntroduction);

    /**
     * 根据ID获取一条通用简介数据
     * @param id 主键ID
     * @return 数据实体
     */
    GeneralIntroduction getIntroById(Long id);

    /**
     * 根据ID查询集合所有数据
     * @param sort 排序类别
     * @param order 排序方向
     * @return 符合条件的集合
     */
    List<GeneralIntroduction> getList(String sort, String order);

    /**
     * 根据ID获取子节点数据
     * @param parentId 父节点ID
     * @param sort 排序类别
     * @param order 排序方向
     * @return 符合条件的集合
     */
    PageResult getChildrenList(Long parentId ,String name, Integer pageNum, Integer pageSize, String sort, String order);

    /**
     * 获取同一层级最大索引值
     * @param parentId 父节点ID
     * @return 最大idx值
     */
    Double getMaxIdx(Long parentId);

    /**
     * 获取同一层级最小索引值
     * @param parentId 父节点ID
     * @return 最小idx值
     */
    Double getMinIdx(Long parentId);

    /**
     * 根据ID更新子节点排序
     * @param parentId 父节点ID
     * @param thisId 此节点ID
     * @param anotherId 另一个需要修改的节点ID
     * @param command 置顶 :top  置底 :end 移动 :move
     * @return 是否操作成功
     */
    Boolean updateIdxById(Long parentId, Long thisId, Long anotherId, String command);

    /**
     * 根据name查询数据
     * @param name 名称
     * @param sort 排序分类
     * @param order 排序方向
     * @return 符合条件的集合
     */
    List<GeneralIntroduction> selectIntroByName(String name, String sort, String order);
}
