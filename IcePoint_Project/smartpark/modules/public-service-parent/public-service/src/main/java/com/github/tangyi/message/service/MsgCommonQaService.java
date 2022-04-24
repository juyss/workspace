package com.github.tangyi.message.service;

import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.message.entity.MsgCommonQa;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName MsgCommonQaService
 * @description
 * @since 2021-05-20 13:55
 */
public interface MsgCommonQaService {

    /**
     * 根据ID查询集合所有数据
     * @param sort 排序类别
     * @param order 排序方向
     * @return 符合条件的集合
     */
    List<MsgCommonQa> getTreeList(String sort, String order);

    /**
     * 根据ID获取子节点数据
     * @param parentId 父节点ID
     * @param sort 排序类别
     * @param order 排序方向
     * @return 符合条件的集合
     */
    PageResult getChildrenList(Long parentId,String name, Integer pageNum, Integer pageSize, String sort, String order);

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
}
