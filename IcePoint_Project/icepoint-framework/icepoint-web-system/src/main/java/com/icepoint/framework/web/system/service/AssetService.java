package com.icepoint.framework.web.system.service;

import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.system.entity.AssetDefine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 资产管理服务接口
 * 预定义资产定义：
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * |  资产编码                                                        |  资产名称                       |    资源编码                                 |   资源名称字段                                      |    资源编码字段                                     |    父资源编码字段
 * |  (assert_code)             |  (assert_name) |    (resource_code)  |   (resource_name_field) |    (resource_code_field) |    (parent_resource_code_field)
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * | WORK_ORD_PLAN_TMPL         | 作业计划内容模板表        |
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * | WORK_ORD_PLAN_TMPL_ITEM    | 作业计划内容模板表        |
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * | WORK_ORD_PLAN              | 作业计划                          |
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * | WORK_ORD_PLAN_SEND         | 作业计划接收表               |
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * | WORK_ORD_TASK              | 作业任务                          |
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * | WORK_ORD_TASK_SEND         | 作业任务派工表               |
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * | WORK_ORD_TASK_CLOCK        | 作业任务打卡表               |
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * | WORK_ORD_CKECK_PNT         | 检查点表                          |
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 * | WORK_ORD_DEFECT            | 作业问题记录表               |
 * |————————————————————————————|————————————————|———————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 *
 * @since 2021-07-07 10:14:58
 */
public interface AssetService {
    /**
     * 根据资产类型查询资产
     *
     * @param assetCode
     */
    Optional<AssetDefine> getAssetDef(String assetCode);

    /**
     * 验证对象字段 验证通过返回true，否则false
     * TODO 排除字段暂时不做处理
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param obj        待验证对象
     * @param exclude    排除验证字段
     */
    Boolean verifyObjField(Long assetDefId, Map<String, Object> obj, List<String> exclude);

    /**
     * 资产定义表子树查询
     * 根据树形结构中给定的节点，查询下级某一级叶子节点，及中间所有层级节点信息，如果未给定指定节点，则从根节点进行查询(从父节点为空开始查)。
     * 算法：调用指定级别子树查询，直接针对资产定义表进行查询，将指定节点到指定层级中的所有资产定义查询出来，以数组形式返回。
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param layers     向下查询级别
     * @param objId      对象ID
     * @param deleted    数据状态
     * @return 子树所有层级节点以列表形式返回
     */
    List<AssetDefine> assetDefTree(Long assetDefId, Integer layers, Long objId, Integer deleted);

    /**
     * 资产定义表列表查询
     */
    List<AssetDefine> assetDefList(AssetDefine entity);

    /**
     * 多分支资产对象查询
     * 给定资产分支数组，每个分支查询一组数据，并将所有数据合并为一个数组。
     * 算法：调用级联关联表对象查询存储过程实现查询。查询字段由字段元数据表定义，看是否会用到关联条件数组和过滤条件数组，如果需要则需要额外处理，可以由外部流程输入。相关的查询条件通过关联资源表、表元数据表、字段元数据表查询。
     *
     * @param branchArray 分支数组
     * @param deleted     数据状态
     * @return 查询到的所有资产对象
     */
    List<Map<String, Object>> objListOfSubTree(List<AssetDefine> branchArray, Integer deleted);

    /**
     * 叶子对象查询
     * 给定资产定义表中的当前节点具体对象，查询下属某一级别的所有节点对象。
     * 涉及数据：资产定义表,具体的资源、表、字段、及对象存储表
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param level      要查询叶子级别
     * @param objId      对象ID
     * @param deleted    数据状态
     */
    List<Map<String, Object>> objLeafList(Long assetDefId, double level, Long objId, Integer deleted);

    /**
     * 资产对象列表查询
     * 通用资产对象列表查询，可以对所有资产对象查询，根据字段元数据表中的字段标识查询。
     * 涉及表：资源表、表元数据表、字段元数据表、对象属性表、对象多属性表、标签表。
     * 如果有扩展字段属于列表字段，需要关联扩展属性表，将其查询出来
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param deleted    数据状态
     * @param filter     查询条件
     */
    List<Map<String, Object>> objList(Long assetDefId, Integer deleted, String filter);

    Page<Map<String, Object>> objList(Long assetDefId, Integer deleted, Pageable pageable, Map<String, Object> filter);

    /**
     * 资产对象树形结构查询
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param deleted    数据状态
     * @param filter     查询条件
     * @return 暂定返回资产id、层级、资源id、资源名称、对象id、对象名称、父对象id、数据状态，后面根据需要扩展其他字段
     */
    List<Map<String, Object>> objTree(Long assetDefId, Integer deleted, String filter);

    /**
     * 资产对象详情查询
     * 通用资产对象查询，可以对所有资产对象类型查询，根据字段元数据表中的字段标识查询。
     * 涉及表：资源表、表元数据表、字段元数据表、对
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param deleted    数据状态
     * @param objId      对象ID
     * @return 返回资产的所有字段
     */
    Map<String, Object> getObjById(Long assetDefId, Integer deleted, Long objId);

    /**
     * 添加所有扩展属性
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param list       对象列表
     * @return 插入个数
     */
    Integer addAllObjAttr(Long assetDefId, List<Map<String, Object>> list);

    /**
     * 修改所有扩展属性
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param list       对象列表
     * @return 插入个数
     */
    Integer updateAllObjAttr(Long assetDefId, List<Map<String, Object>> list);

    /**
     * 通过对象Id删除所有扩展属性
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param list       对象Id列表
     * @return 删除个数
     */
    Integer deleteAllObjAttrByObjId(Long assetDefId, List<Long> list);

    /**
     * 根据表元数据表ID和对象ID，查询此对象的扩展属性
     *
     * @param tableId 表ID
     * @param objId   对象ID
     * @return List<Map < String, Object>>
     * @author Juyss
     */
    Map<String, Object> findAllObjAttrById(Long tableId, Long objId);

	/**
	 * 向上递归查询资产的所有父节点 不包含自身
	 * @param parentId 父节点Id
	 * @param list 容器
	 * @return 返回资产的所有父节点 不包含自身
	 */
	Collection<AssetDefine> assetDefUpTree(Long parentId, Collection<AssetDefine>list);

	/**
	 * 根据资产id 和 objId 查询物理表数据
	 * @param assetId 资产id
	 * @param objId 对象Id
	 * @return 物理表数据
	 */
	Map<String,Object> selectOn(Long assetId, List<Long> objId);

	/**
	 * 根据表元数据表ID和对象ID,查询此对象的扩展属性实体列表 返回单属性  多属性 扩展字段
	 * @param tableId 元数据表Id
	 * @param objId 对象Id
	 */
	List<Map<String,Object>> findAllObj(Long tableId,Long objId);
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//  PC-资产定义
	//////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 新增资产定义
	 *
	 * @param entity 资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetsDefine}
	 * @return 返回新增后的资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetsDefine}
	 */
	AssetDefine addAssetDef(AssetDefine entity);


	/**
	 * 修改资产定义
	 *
	 * @param entity 资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetsDefine}
	 * @return 返回修改后的资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetsDefine}
	 */
	AssetDefine updateAssetDef(AssetDefine entity);

	/**
	 * 删除资产定义
	 *
	 * @param ids 要删除的数据主键
	 * @return 返回删除个数
	 */
	Integer deleteAssetDef(List<Long> ids);

	/**
	 * 资产定义分页查询
	 *
	 * @param filter 查询条件  {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetsDefine}
	 * @param pageable 分页参数  {@linkplain org.springframework.data.domain.Pageable Pageable}
	 * @return 资产定义分页列表 List<{@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetsDefine}>
	 */
	Page<AssetDefine> assetDefList(AssetDefine filter, Pageable pageable);

	/**
	 * 资产定义树形结构
	 *
	 * @param rootId 树形结构根节点
	 * @return 树形结构
	 */
	List<TreeNode<AssetDefine>> assetDefTree(Long rootId);

	/**
	 * 资产定义详情查询
	 *
	 * @param id 资产定义ID
	 * @return 返回资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetsDefine}
	 */
	AssetDefine getAssetDefById(Long id);

	/**
	 * 资产定义顺序设置
	 *
	 * @param parentId  父节点
	 * @param thisId 当前节点
	 * @param anotherId 另一个需要修改的节点ID
	 * @param command 设置命令
	 * @return 是否成功
	 */
	Boolean moveAssetDefById(Long parentId, Long thisId, Long anotherId, String command);

	//////////////////////////////////////////////////////////////////////////////////////////////////
	//  PC-资产对象
	//////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 新增资产对象
	 *
	 * @param assetDefId 资产定义ID
	 * @param entity 资产对象
	 * @return 返回新增后的资产对象
	 */
	Map<String,Object> addAssetObj(Long assetDefId,Map<String,Object> entity);

	/**
	 * 修改资产对象
	 *
	 * @param assetDefId 资产定义ID
	 * @param entity 资产对象
	 * @return 返回修改后的资产对象
	 */
	Map<String,Object> updateAssetObj(Long assetDefId,Map<String,Object> entity);

	/**
	 * 删除资产对象
	 *
	 * @param assetDefId 资产定义ID
	 * @param ids 要删除的数据主键
	 * @return 返回删除个数
	 */
	Integer deleteAssetObj(Long assetDefId,List<Long> ids);

	/**
	 * 根据资源id查询资产
	 *
	 * @author Juyss
	 * @param resourceId 资源id
	 * @return Optional<AssetDefine>
	 */
	Optional<AssetDefine> findByResourceId(Long resourceId);

	/**
	 * 用户绑定资产
	 * @param map
	 * @return
	 */
    Map<String,Object> binDing(Map<String, Object> map);

	/**
	 * 接触绑定
	 * @param assestid 资产Id
	 * @return 是否成功
	 */
	Boolean unBingDing(Long assestid);

	/**
	 * 根据id查询绑定的资产
	 * @param userId 用户id
	 * @return 资产集合
	 */
	List<AssetDefine> queryAssetByUserid(Long userId);

	/**
	 * 查询未绑定的资产
	 * @return
	 */
	List<AssetDefine> queryUnBingDing();
}
