package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.AssetDefine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产管理数据库访问层
 *
 * @since 2021-07-07 09:20:09
 */
@Mapper
public interface AssetMapper extends RepositoryMapper<AssetDefine, Long> {

	/**
	 * 资产定义表子树查询
	 * 根据树形结构中给定的节点，查询下级某一级叶子节点，及中间所有层级节点信息，如果未给定指定节点，则从根节点进行查询(从父节点为空开始查)。
	 * 算法：调用指定级别子树查询，直接针对资产定义表进行查询，将指定节点到指定层级中的所有资产定义查询出来，以数组形式返回。
	 *
	 * @param assetDefId 资产定义表资产定义ID
	 * @param layers 向下查询级别
	 * @param objId 对象ID
	 * @param deleted 数据状态
	 * @return 所有数据
	 */
	List<AssetDefine> assetDefTree(Long assetDefId,Integer layers,Long objId,Integer deleted);

	/**
	 * 多分支资产对象查询
	 * 给定资产分支数组，每个分支查询一组数据，并将所有数据合并为一个数组。
	 * 算法：调用级联关联表对象查询存储过程实现查询。查询字段由字段元数据表定义，看是否会用到关联条件数组和过滤条件数组，如果需要则需要额外处理，可以由外部流程输入。相关的查询条件通过关联资源表、表元数据表、字段元数据表查询。
	 * @param branchArray 分支数组
	 * @param deleted 数据状态
	 * @return List
	 */
	List<Map<String, Object>> objListOfSubTree(List<AssetDefine> branchArray,Integer deleted);

	/**
	 * 根据ids批量查询
	 */
	List<AssetDefine> queryByIds(List<Long> ids);

	/**
	 *查询物理表数据
	 * @param nameEn 表名
	 * @param objId 查询的id
	 */
	Map<String, Object> select(@Param("tableName") String nameEn,@Param("objId") List<Long> objId);
}
