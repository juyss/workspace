package com.github.tangyi.introduction.mapper;

import com.github.tangyi.introduction.entity.GeneralIntroduction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: GeneralIntroductionMapper
 * @Desc: Mapper层接口
 * @Author Juyss
 * @Date: 2021-04-16 11:22
 * @Version 1.0
 */
@Mapper
@Repository
public interface GeneralIntroductionMapper {

    /**
     * 添加一条数据，完整插入
     * @param generalIntroduction
     * @return
     */
    int insert(GeneralIntroduction generalIntroduction);

    /**
     * 选择插入
     * @param generalIntroduction
     * @return
     */
    int insertSelective(GeneralIntroduction generalIntroduction);

    /**
     * 更新一条数据
     * @param generalIntroduction
     * @return
     */
    int updateByPrimaryKey(GeneralIntroduction generalIntroduction);

    /**
     * 选择更新
     * @param generalIntroduction
     * @return
     */
    int updateByPrimaryKeySelective(GeneralIntroduction generalIntroduction);

    /**
     * 逻辑删除一条数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 根据ID获取一条通用简介数据
     * @param id 主键ID
     * @return 数据实体
     */
    GeneralIntroduction getIntroById(Long id);

    /**
     * 获取树形结构
     * @param sort
     * @param order
     * @return
     */
    List<GeneralIntroduction> getList(@Param("sort")String sort, @Param("order")String order);

    /**
     * 根据ID获取子节点数据
     *
     * @param parentId    父节点ID
     * @param sort  排序类别
     * @param order 排序方向
     * @return 符合条件的集合
     */
    List<GeneralIntroduction> getChildrenList(@Param("parentId") Long parentId,@Param("name") String name ,@Param("sort") String sort, @Param("order") String order);

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

    /**
     * 根据ID更新同级排序索引
     * @param id 主键ID
     * @param idx 新的索引值
     * @return 修改成功的条数
     */
    int updateIdxById(@Param("id") Long id,@Param("idx") Double idx);

    /**
     * 根据name查询数据
     *
     * @param name  名称
     * @param sort  排序分类
     * @param order 排序方向
     * @return 符合条件的集合
     */
    List<GeneralIntroduction> selectIntroByName(@Param("name") String name, @Param("sort") String sort, @Param("order") String order);
}