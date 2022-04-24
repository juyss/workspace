package com.github.tangyi.message.mapper;

import com.github.tangyi.message.entity.MsgCommonQa;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName MsgCommonQaMapper
 * @description
 * @since 2021-05-20 11:53
 */
@Repository
public interface MsgCommonQaMapper {

    int deleteByPrimaryKey(Long id);

    int insert(MsgCommonQa record);

    int insertSelective(MsgCommonQa record);

    MsgCommonQa selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgCommonQa record);

    int updateByPrimaryKey(MsgCommonQa record);

    /**
     * 查询所有数据
     * @param sort
     * @param order
     * @return
     */
    List<MsgCommonQa> getList(@Param("sort") String sort, @Param("order") String order);

    /**
     * 根据ID获取子节点数据
     *
     * @param parentId    父节点ID
     * @param sort  排序类别
     * @param order 排序方向
     * @return 符合条件的集合
     */
    List<MsgCommonQa> getChildrenList(@Param("parentId") Long parentId,@Param("name") String name, @Param("sort") String sort, @Param("order") String order);

    /**
     * 根据ID更新同级排序索引
     * @param id 主键ID
     * @param idx 新的索引值
     * @return 修改成功的条数
     */
    int updateIdxById(@Param("id") Long id,@Param("idx") Double idx);

    /**
     * 获取同一层级最大的索引值
     * @param parentId 父节点ID
     * @return 最大索引值
     */
    Double getMaxIdx(@Param("parentId")Long parentId);

    /**
     * 获取同一层级最小索引值
     * @param parentId 父节点ID
     * @return 最小索引值
     */
    Double getMinIdx(@Param("parentId") Long parentId);
}
