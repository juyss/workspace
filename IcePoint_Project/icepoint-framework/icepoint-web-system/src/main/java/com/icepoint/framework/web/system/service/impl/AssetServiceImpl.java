package com.icepoint.framework.web.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.data.util.TreeUtils;
import com.icepoint.framework.web.system.dao.*;
import com.icepoint.framework.web.system.entity.*;
import com.icepoint.framework.web.system.resource.*;
import com.icepoint.framework.web.system.resource.builder.DefaultLookupBuilder;
import com.icepoint.framework.web.system.service.AssetService;
import com.icepoint.framework.web.system.service.ResourceMetadataService;
import com.icepoint.framework.web.system.service.ResourceModelService;
import com.icepoint.framework.web.system.service.ResourceService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName AssetServiceImpl
 * @description
 * @since 2021-07-12 15:37
 */
@Service
public class AssetServiceImpl implements AssetService {

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private AssetRepository assetRepository;

    @Resource
    private ResourceService resourceService;

    @Resource
    private ResourceMetadataService resourceMetadataService;

    @Resource
    private ResourceModelService resourceModelService;

    @Resource
    private DefaultResourceModelViewResolver defaultResourceModelViewResolver;

    @Resource
    private AttributeMapper attributeMapper;

    @Resource
    private LabelMapper labelMapper;

    @Resource
    private LabelDefMapper labelDefMapper;


    @Override
    public Optional<AssetDefine> getAssetDef(String assetCode) {
        LambdaQueryWrapper<AssetDefine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AssetDefine::getAssetCode, assetCode);
        return assetMapper.findOne(queryWrapper);
    }


    /**
     * 验证必填字段是否都有填写
     * TODO 排除字段暂时不做处理
     *
     * @param assetDefId 资产定义ID
     * @param obj        对象
     * @param exclude    排除字段
     * @return Boolean
     * @author Juyss
     */
    @Override
    public Boolean verifyObjField(Long assetDefId, Map<String, Object> obj, List<String> exclude) {
        // TODO Auto-generated method stub
        //查询资产定义
        AssetDefine assetDefine = assetMapper.findById(assetDefId)
                .orElseThrow(() -> new IllegalArgumentException("资产定义数据不存在"));
        Long resourceId = assetDefine.getResourceId();
        //查询元数据信息
        TableMetadata info = resourceService.getInfoByResourceId(resourceId);
        List<FieldMetadata> fieldMetadatas = info.getFieldMetadatas();
        //获得必填字段集合
        List<String> list = fieldMetadatas.stream()
                .filter(FieldMetadata::getOptional)
                .map(FieldMetadata::getNameEn)
                .collect(Collectors.toList());

        //设置标记
        AtomicBoolean flag = new AtomicBoolean(true);
        //逐个对比
        obj.forEach((key, value) -> {

            //判断key是否存在于必填字段集合中
            if (list.contains(key)) {

                //判断value是否有值
                if (ObjectUtils.isEmpty(value)) {
                    //存在必填字段没有值的话，标记为false
                    flag.set(false);
                }
            }
        });
        return flag.get();
    }

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
     * @author Juyss
     */
    @Override
    public List<AssetDefine> assetDefTree(Long assetDefId, Integer layers, Long objId, Integer deleted) {

        //定义结果集
        ArrayList<AssetDefine> result = new ArrayList<>();

        //定义父节点ID
        Long parentId = assetDefId;

        //如果父节点ID为空，从根节点开始查询，如果父节点ID不为空，就从指定节点开始查
        LambdaQueryWrapper<AssetDefine> wrapper = new LambdaQueryWrapper<>();
        if (parentId != null) {
            wrapper.eq(AssetDefine::getParentId, parentId);
        } else {
            wrapper.isNull(AssetDefine::getParentId);
        }
        //父节点数据
        AssetDefine rootNode = assetMapper.findOne(wrapper)
                .orElseThrow(() -> new IllegalArgumentException("父节点数据不存在"));

        //添加到结果集
        result.add(rootNode);

        //单分支节点，控制查询层级
//        for (int i = 0; i <= layers; i++) {
//            wrapper.clear();
//            wrapper.eq(AssetsDefine::getParentId,parentId);
//            Optional<AssetsDefine> assetsDefineOptional = assetsMapper.findOne(wrapper);
//            if (!assetsDefineOptional.isPresent()){
//                break;
//            }
//            AssetsDefine children = assetsDefineOptional.get();
//            result.add(children);
//        }

        //多分支节点，控制查询层级
        List<AssetDefine> list = new ArrayList<>();
        list.add(rootNode);

        //通过for循环次数控制查询子树的层级
        for (; layers > 0; layers--) {
            if (list == null || list.size() == 0) {
                break;
            }
            List<AssetDefine> childrenList = getChildren(list);
            list = childrenList;
            result.addAll(childrenList);
        }

        return result;
    }

    /**
     * 资产定义表列表查询
     */
    @Override
    public List<AssetDefine> assetDefList(AssetDefine entity) {
        if (!ObjectUtils.isEmpty(entity.getIds())) {

            String ids = entity.getIds();
            Long[] strArrNum = (Long[]) ConvertUtils.convert(ids.split(","), Long.class);
            List<AssetDefine> list = assetMapper.queryByIds(Arrays.asList(strArrNum));

            return list;
        }


        return null;
    }

    /**
     * 多分支资产对象查询
     * 给定资产分支数组，每个分支查询一组数据，并将所有数据合并为一个数组。
     * 算法：调用级联关联表对象查询存储过程实现查询。查询字段由字段元数据表定义，看是否会用到关联条件数组和过滤条件数组，如果需要则需要额外处理，可以由外部流程输入。相关的查询条件通过关联资源表、表元数据表、字段元数据表查询。
     *
     * @param branchArray 分支数组
     * @param deleted     数据状态
     * @return 查询到的所有资产对象
     */
    @Override
    public List<Map<String, Object>> objListOfSubTree(List<AssetDefine> branchArray, Integer deleted) {

        //获取资产定义ID集合
        List<Long> assetDefIdList = branchArray.stream().map(AssetDefine::getId).collect(Collectors.toList());

        //根据资产定义ID查询对象列表
        List<Map<String, Object>> resultList = new ArrayList<>();

        assetDefIdList.forEach(id -> {
            //查询对象集合
            List<Map<String, Object>> objList = objList(id, 0, null);
            //添加到结果集中
            resultList.addAll(objList);
        });

        return resultList;
    }

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
    @Override
    public List<Map<String, Object>> objLeafList(Long assetDefId, double level, Long objId, Integer deleted) {
        //通过id 查询对应的资产
        AssetDefine assetDefine = assetMapper.findById(assetDefId)
                .orElseThrow(() -> new IllegalArgumentException("资产信息为空"));
        //获取所有的元数据表
        TableMetadata infoByResourceCode = resourceService.getInfoByResourceId(assetDefine.getResourceId());
        //获取对象存储


        return null;
    }

    /**
     * 资产对象列表查询
     * 通用资产对象列表查询，可以对所有资产对象查询，根据字段元数据表中的字段标识查询。
     * 涉及表：资源表、表元数据表、字段元数据表、对象属性表、对象多属性表、标签表。
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param deleted    数据状态
     * @param filter     查询条件
     */
    @Override
    public List<Map<String, Object>> objList(Long assetDefId, Integer deleted, String filter) {
        //通过id 查询对应的资产
        AssetDefine assetDefine = assetMapper.findById(assetDefId)
                .orElseThrow(() -> new IllegalArgumentException("资产信息为空"));

        //获取资产编码
        String assetCode = assetDefine.getAssetCode();

        //根据资产编码获取元数据
        ResourceModelMetadata resourceModelMetadata = resourceMetadataService.findByAssetCode(assetCode);

        //构建查询对象
        Lookup lookup = DefaultLookupBuilder.of(resourceModelMetadata)
                .viewType(ViewType.SIMPLE)
                .build();

        List<ResourceModel> modelList = resourceModelService.findAll(lookup);

        //构建结果集
        List<Map<String, Object>> result = new ArrayList<>();

        //解析数据并将结果添加到结果集
        modelList.forEach(resourceModel -> {
            Map<String, Object> resolve = defaultResourceModelViewResolver.resolve(resourceModel);
            result.add(resolve);
        });

        return result;
    }

    @Override
    public Page<Map<String, Object>> objList(Long assetDefId, Integer deleted, Pageable pageable,
                                             Map<String, Object> filter) {
        // TODO Auto-generated method stub
        //通过id 查询对应的资产
        AssetDefine assetDefine = assetMapper.findById(assetDefId)
                .orElseThrow(() -> new IllegalArgumentException("资产信息为空"));

        //获取资产编码
        String assetCode = assetDefine.getAssetCode();

        //根据资产编码获取元数据
        ResourceModelMetadata resourceModelMetadata = resourceMetadataService.findByAssetCode(assetCode);

        //构建查询对象
        Lookup lookup = DefaultLookupBuilder.of(resourceModelMetadata)
                .viewType(ViewType.SIMPLE)
                .build();

        //查询对象
        Page<ResourceModel> page = resourceModelService.findAll(lookup, pageable);

        //构建结果集
        ArrayList<Map<String, Object>> result = new ArrayList<>();

        //解析数据并将结果添加到结果集
        page.get().forEach(resourceModel -> {
            Map<String, Object> resolve = defaultResourceModelViewResolver.resolve(resourceModel);
            result.add(resolve);
        });

        //重新构建分页对象
        PageImpl<Map<String, Object>> mapPage = new PageImpl<>(result, pageable, page.getTotalElements());


        return mapPage;
    }

    /**
     * 资产对象树形结构查询
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param deleted    数据状态
     * @param filter     查询条件
     * @return 暂定返回资产id、层级、资源id、资源名称、对象id、对象名称、父对象id、数据状态，后面根据需要扩展其他字段
     */
    @Override
    public List<Map<String, Object>> objTree(Long assetDefId, Integer deleted, String filter) {
        return null;
    }

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
    @Override
    public Map<String, Object> getObjById(Long assetDefId, Integer deleted, Long objId) {
        //通过id 查询对应的资产
        AssetDefine assetDefine = assetMapper.findById(assetDefId)
                .orElseThrow(() -> new IllegalArgumentException("资产信息为空"));

        //获取所有的元数据表
        TableMetadata metadata = resourceService.getInfoByResourceId(assetDefine.getResourceId());

        //获取表ID
        Long tableId = metadata.getId();

        //查询对象数据
        String assetCode = assetDefine.getAssetCode();
        ResourceModelMetadata resourceModelMetadata = resourceMetadataService.findByAssetCode(assetCode);

        //构建查询对象
        Lookup lookup = DefaultLookupBuilder.of(resourceModelMetadata)
                .viewType(ViewType.SIMPLE)
                .build();

        //查询对象数据
        ResourceModel resourceModel = resourceModelService.findById(lookup, objId)
                .orElseThrow(() -> new IllegalArgumentException("对象不存在"));

        //解析数据
        Map<String, Object> result = defaultResourceModelViewResolver.resolve(resourceModel);

        //查询对象的扩展属性
        Map<String, Object> allObjAttrById = findAllObjAttrById(tableId, objId);
        result.putAll(allObjAttrById);

        return result;
    }

    /**
     * 添加所有扩展属性值
     *
     * @param assetDefId 资产定义表资产定义ID
     * @param list       对象列表
     * @return 插入个数
     */
    @Override
    public Integer addAllObjAttr(Long assetDefId, List<Map<String, Object>> list) {
        //通过assetDefId 查询资产
        AssetDefine assetDefine = assetMapper.findById(assetDefId)
                .orElseThrow(() -> new IllegalArgumentException("资产为空"));
        Long resourceId = assetDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        //获取所有的字段
        List<FieldMetadata> fields = tableMetadata.getFieldMetadatas();
        //获取单属性的 值
        List<FieldMetadata> onAttribute = fields.stream()
                .filter(filed -> filed.getType() == 1)
                .collect(Collectors.toList());
        //新增单属性扩展字段
        if (!ObjectUtils.isEmpty(onAttribute)) {
            for (Map<String, Object> map : list) {
                for (FieldMetadata fieldMetadata : onAttribute) {
                    if (map.containsKey(fieldMetadata.getNameEn())) {
                        //如果存在 新增一条数据
                        Attribute attribute = new Attribute();
                        attribute.setObjId(Long.parseLong(map.get("id").toString()));
                        attribute.setTableId(tableMetadata.getId());
                        attribute.setName(fieldMetadata.getNameEn());
                        attribute.setValue(map.get(fieldMetadata.getNameEn()).toString());
                        attributeMapper.insert(attribute);
                    }
                }
            }
        }


        //新增多属性扩展字段
        return onAttribute.size();
    }

    /**
     * 更新扩展字段
     *
     * @param assetDefId 资产定义ID
     * @param list       对象列表
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer updateAllObjAttr(Long assetDefId, List<Map<String, Object>> list) {
        //通过assetDefId 查询资产
        AssetDefine assetDefine = assetMapper.findById(assetDefId)
                .orElseThrow(() -> new IllegalArgumentException("资产为空"));
        Long resourceId = assetDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        //获取所有的字段
        List<FieldMetadata> fields = tableMetadata.getFieldMetadatas();
        //获取单属性的 值
        List<FieldMetadata> onAttribute = fields.stream()
                .filter(filed -> filed.getType() == 1)
                .collect(Collectors.toList());

        LambdaQueryWrapper<Attribute> attributeQueryWrapper = Wrappers.lambdaQuery(Attribute.class);
        LambdaUpdateWrapper<Attribute> attributeUpdateWrapper = Wrappers.lambdaUpdate(Attribute.class);

        //更新单属性扩展字段
        if (!ObjectUtils.isEmpty(onAttribute)) {
            for (Map<String, Object> map : list) {
                for (FieldMetadata fieldMetadata : onAttribute) {
                    if (map.containsKey(fieldMetadata.getNameEn())) {
                        //清除条件封装
                        attributeQueryWrapper.clear();
                        attributeUpdateWrapper.clear();
                        //查询指定扩展属性
                        attributeQueryWrapper.eq(Attribute::getObjId, Long.parseLong(map.get("id").toString()));
                        attributeQueryWrapper.eq(Attribute::getTableId, tableMetadata.getId());
                        attributeQueryWrapper.eq(Attribute::getName, fieldMetadata.getNameEn());
                        Attribute attribute = attributeMapper.findOne(attributeQueryWrapper)
                                .orElseThrow(() -> new IllegalArgumentException("扩展属性不存在"));
                        //更新扩展属性
                        String oldValue = attribute.getValue();
                        String newValue = map.get(fieldMetadata.getNameEn()).toString();
                        //如果 newValue不为空 且 oldValue与newValue不相等，更新数据
                        if (newValue != null && (!oldValue.contentEquals(newValue))) {
                            attributeUpdateWrapper.set(Attribute::getValue, newValue);
                            attributeMapper.update(attribute, attributeUpdateWrapper);
                        }
                    }
                }
            }
        }

        return onAttribute.size();
    }

    /**
     * 删除扩展属性
     *
     * @param assetDefId 资产定义ID
     * @param list       对象ID集合
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer deleteAllObjAttrByObjId(Long assetDefId, List<Long> list) {

        //通过assetDefId 查询资产
        AssetDefine assetDefine = assetMapper.findById(assetDefId)
                .orElseThrow(() -> new IllegalArgumentException("资产为空"));
        Long resourceId = assetDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        //获取表ID
        Long tableId = tableMetadata.getId();

        LambdaQueryWrapper<Attribute> attributeQueryWrapper = Wrappers.lambdaQuery(Attribute.class);
        //待删除ID集合
        ArrayList<Long> attributeIdList = new ArrayList<>();

        for (Long objId : list) {
            attributeQueryWrapper.clear();
            attributeQueryWrapper.eq(Attribute::getObjId, objId);
            attributeQueryWrapper.eq(Attribute::getTableId, tableId);
            List<Attribute> attributeList = attributeMapper.findAll(attributeQueryWrapper);
            //获取对象ID的扩展属性列表数据ID集合
            List<Long> collect = attributeList.stream().map(Attribute::getId).collect(Collectors.toList());
            //添加到待删除ID集合
            attributeIdList.addAll(collect);
        }
        if (ObjectUtils.isEmpty(attributeIdList)) {
            return null;
        }
        //删除扩展属性数据
        int deleteInBatchIds = attributeMapper.deleteInBatchIds(attributeIdList);

        return deleteInBatchIds;
    }

    /**
     * 根据表元数据表ID和对象ID，查询此对象的扩展属性
     *
     * @param tableId 表ID
     * @param objId   对象ID
     * @return List<Map < String, Object>>
     * @author Juyss
     */
    @Override
    public Map<String, Object> findAllObjAttrById(Long tableId, Long objId) {

        LambdaQueryWrapper<Attribute> attributeQueryWrapper = Wrappers.lambdaQuery(Attribute.class);

//        List<Map<String, Object>> resultList = null;
        Map<String, Object> resultMap = null;

        if (tableId != null && objId != null) {
            attributeQueryWrapper.eq(Attribute::getObjId, objId);
            attributeQueryWrapper.eq(Attribute::getTableId, tableId);
            //查询所有扩展属性信息
            List<Attribute> attributeList = attributeMapper.findAll(attributeQueryWrapper);

            //取属性name和value作为map的K,V值，重新生成集合
//            resultList = attributeList.stream().map((Function<Attribute, Map<String, Object>>) attribute -> {
//                HashMap<String, Object> map = new HashMap<>();
//                map.put(attribute.getName(), attribute.getValue());
//                return map;
//            }).collect(Collectors.toList());

            //取属性name和value作为map的K,V值，重新生成新的map
            resultMap = attributeList.stream().collect(Collectors.toMap(Attribute::getName, Attribute::getValue));

        }
        return resultMap;
    }

    /**
     * 根据资产父id 向上查询所有父资产
     */
    @Override
    public Collection<AssetDefine> assetDefUpTree(Long parentId, Collection<AssetDefine> list) {
        if (ObjectUtils.isEmpty(list)) {
            list = new ArrayList<>();
        }
        Optional<AssetDefine> byId = assetMapper.findById(parentId);
        if (byId.isPresent()) {
            list.add(byId.get());
            assetDefUpTree(byId.get().getParentId(), list);
        }
        return list;
    }

    @Override
    public Map<String, Object> selectOn(Long assetId, List<Long> objId) {
        //根据assetId 获取 resource
        AssetDefine assetDefine = assetMapper.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("未定义资产"));
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(assetDefine.getResourceId());
        //获取表名
        String nameEn = tableMetadata.getName();
        Map<String, Object> select = assetMapper.select(nameEn, objId);
        return select;
    }

    @Override
    public List<Map<String, Object>> findAllObj(Long tableId, Long objId) {
        LambdaQueryWrapper<Attribute> attributeQueryWrapper = Wrappers.lambdaQuery(Attribute.class);
        if (ObjectUtils.isEmpty(tableId) || ObjectUtils.isEmpty(objId)) {
            throw new IllegalArgumentException("tableId --objId不能为空");
        }
        attributeQueryWrapper.eq(Attribute::getObjId, objId);
        attributeQueryWrapper.eq(Attribute::getTableId, tableId);
        List<Attribute> attributeList = attributeMapper.findAll(attributeQueryWrapper);
        //单属性扩展字段
        List<Map<String, Object>> list = attributeList.stream().map(item -> {
            Map<String, Object> map = MapUtils.objectToMap(item);
            return map;
        }).collect(Collectors.toList());
        //TODO 多属性扩展字段
        return list;
    }

    /**
     * 查询集合的子节点数据，然后将子节点
     *
     * @param list
     * @return List<AssetsDefine>
     */
    private List<AssetDefine> getChildren(List<AssetDefine> list) {

        if (list == null || list.size() == 0) {
            return null;
        }
        LambdaQueryWrapper<AssetDefine> wrapper = new LambdaQueryWrapper<>();

        Long parentId;
        ArrayList<AssetDefine> childrenList = new ArrayList<>();

        for (AssetDefine assetDefine : list) {
            wrapper.clear();
            parentId = assetDefine.getId();
            wrapper.eq(AssetDefine::getParentId, parentId);
            List<AssetDefine> assetDefineList = assetMapper.findAll(wrapper);
            childrenList.addAll(assetDefineList);
        }
        return childrenList;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-资产定义
    //////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 添加资产定义
     *
     * @param entity
     * @return AssetDefine
     * @author Juyss
     */
    @Override
    public AssetDefine addAssetDef(AssetDefine entity) {
        assetMapper.insert(entity);
        String assetCode = entity.getAssetCode();
        LambdaQueryWrapper<AssetDefine> wrapper = Wrappers.lambdaQuery(AssetDefine.class);
        wrapper.eq(AssetDefine::getAssetCode, assetCode);
        AssetDefine assetDefine = assetMapper.findOne(wrapper).orElseThrow(() -> new IllegalArgumentException(String.format("资产定义保存失败,资产编码: %s", assetCode)));
        return assetDefine;
    }


    /**
     * 修改资产定义
     *
     * @param entity
     * @return AssetDefine
     * @author Juyss
     */
    @Override
    public AssetDefine updateAssetDef(AssetDefine entity) {

        Long id = entity.getId();
        String assetCode = entity.getAssetCode();

        LambdaQueryWrapper<AssetDefine> wrapper = Wrappers.lambdaQuery(AssetDefine.class);
        wrapper.eq(AssetDefine::getId, id);
        wrapper.eq(AssetDefine::getAssetCode, assetCode);
        //查询旧数据
        assetMapper.findOne(wrapper).orElseThrow(() -> new IllegalArgumentException(String.format("资产定义不存在,资产id: %s,资产编码: %s", id, assetCode)));
        //更新数据
        assetMapper.update(entity);
        //查询新数据
        AssetDefine assetDefine = assetMapper.findOne(wrapper).orElseThrow(() -> new IllegalArgumentException(String.format("资产定义查询失败,资产id: %s,资产编码: %s", id, assetCode)));
        return assetDefine;
    }


    /**
     * 批量删除资产定义
     *
     * @param ids 要删除的id集合
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer deleteAssetDef(List<Long> ids) {
        // FIXME 需要做删除判断
        return assetMapper.deleteInBatchIds(ids);
    }


    /**
     * 资产对象分页查询
     *
     * @param filter   查询条件
     * @param pageable 分页参数
     * @return Page<AssetDefine>
     * @author Juyss
     */
    @Override
    public Page<AssetDefine> assetDefList(AssetDefine filter, Pageable pageable) {
        //生成查询条件
        Map<String, Object> lineMap = MapUtils.objectToLineMap(filter);
        QueryWrapper<AssetDefine> wrapper = new QueryWrapper<>();
        wrapper.allEq(lineMap);
        return assetMapper.findAll(wrapper, pageable);
    }


    /**
     * 资产树形结构查询
     *
     * @param rootId 根节点id
     * @return List<TreeNode < AssetDefine>>
     * @author Juyss
     */
    @Override
    public List<TreeNode<AssetDefine>> assetDefTree(Long rootId) {
        // TODO Auto-generated method stub
        //判断根节点是否存在
        LambdaQueryWrapper<AssetDefine> wrapper = Wrappers.lambdaQuery(AssetDefine.class);
        wrapper.eq(AssetDefine::getId, rootId);
        AssetDefine rootNode = assetMapper.findOne(wrapper).orElseThrow(() -> new IllegalArgumentException("根节点不存在"));
        List<AssetDefine> assetDefineList = assetRepository.findAll();
        List<TreeNode<AssetDefine>> treeNode = TreeUtils.buildTreeStructure(assetDefineList, rootId);
        return treeNode;
    }

    /**
     * 资产详情查询
     *
     * @param id 主键
     * @return AssetDefine
     * @author Juyss
     */
    @Override
    public AssetDefine getAssetDefById(Long id) {
        return assetMapper.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("资产定义不存在,资产id: %s", id)));
    }


    @Override
    public Boolean moveAssetDefById(Long parentId, Long thisId, Long anotherId, String command) {
        //如果命令移动命令为空，返回false
        if (ObjectUtils.isEmpty(command)) {
            return false;
        }

        //更新数量
        boolean flag = false;

        //置顶，设置此节点 idx=MIN(idx)-0.1
        if ("top".equalsIgnoreCase(command)) {

            //查询最小索引
            LambdaQueryWrapper<AssetDefine> wrapper = Wrappers.lambdaQuery(AssetDefine.class);
            wrapper.eq(AssetDefine::getParentId, parentId).orderByAsc(AssetDefine::getLevel).last("limit 1");
            Optional<AssetDefine> optional = assetMapper.findOne(wrapper);
            if (optional.isPresent()) {
                AssetDefine assetDefine = optional.get();

                //得到最小索引
                int minOrder = assetDefine.getLevel();
                wrapper.clear();
                wrapper.eq(AssetDefine::getId, thisId);
                Optional<AssetDefine> thisOptional = assetMapper.findOne(wrapper);
                if (thisOptional.isPresent()) {
                    AssetDefine define = thisOptional.get();
                    //更新节点索引值
                    define.setLevel(minOrder - 1);
                    int updated = assetMapper.update(define);
                    flag = updated == 1;
                }
            }

        }

        //置底，设置此节点 idx=MAX(idx)+0.1
        if ("end".equalsIgnoreCase(command)) {
            //查询最大索引
            LambdaQueryWrapper<AssetDefine> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AssetDefine::getParentId, parentId).orderByDesc(AssetDefine::getLevel).last("limit 1");
            Optional<AssetDefine> optional = assetMapper.findOne(wrapper);
            if (optional.isPresent()) {
                AssetDefine assetDefine = optional.get();

                //得到最大索引
                int maxOrder = assetDefine.getLevel();
                wrapper.clear();
                wrapper.eq(AssetDefine::getId, thisId);
                Optional<AssetDefine> thisOptional = assetMapper.findOne(wrapper);
                if (thisOptional.isPresent()) {
                    AssetDefine define = thisOptional.get();
                    //更新节点索引值
                    define.setLevel(maxOrder + 1);
                    int updated = assetMapper.update(define);
                    flag = updated == 1;
                }
            }
        }

        //移动，将两个节点idx数值进行交换
        if ("move".equalsIgnoreCase(command)) {
            //先查询是否有数据
            Optional<AssetDefine> thisOptional = assetMapper.findById(thisId);
            Optional<AssetDefine> anotherOptional = assetMapper.findById(anotherId);

            if (thisOptional.isPresent() && anotherOptional.isPresent()) {
                //获取节点数据
                AssetDefine thisNode = thisOptional.get();
                AssetDefine anotherNode = anotherOptional.get();
                int thisOrder = thisNode.getLevel();
                int anotherOrder = anotherNode.getLevel();
                //order值调换
                thisNode.setLevel(anotherOrder);
                anotherNode.setLevel(thisOrder);
                //更新数据
                int thisUpdated = assetMapper.update(thisNode);
                int anotherUpdated = assetMapper.update(anotherNode);

                flag = thisUpdated + anotherUpdated == 2;
            }
        }

        return flag;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-资产对象
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Map<String, Object> addAssetObj(Long assetDefId, Map<String, Object> entity) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Map<String, Object> updateAssetObj(Long assetDefId, Map<String, Object> entity) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Integer deleteAssetObj(Long assetDefId, List<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 根据资源id查询资产
     *
     * @param resourceId 资源id
     * @return Optional<AssetDefine>
     * @author Juyss
     */
    @Override
    public Optional<AssetDefine> findByResourceId(Long resourceId) {
        Assert.notNull(resourceId, MessageTemplates.notNull("资源id"));
        return assetRepository.findOne(QAssetDefine.assetDefine.resourceId.eq(resourceId), false);
    }


    @Override
    public Map<String, Object> binDing(Map<String, Object> map) {
        if (ObjectUtils.isEmpty(map)) {
            throw new IllegalArgumentException("参数为空");
        }
        //获取资产
        AssetDefine assetDefine = JSON.parseObject(JSON.toJSONString(map), AssetDefine.class);
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(assetDefine.getResourceId());
        //标签查询
        LambdaQueryWrapper<Label> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Label::getObjId, assetDefine.getId());
        queryWrapper.eq(Label::getTableId, tableMetadata.getId());
        List<Label> labels = labelMapper.findAll(queryWrapper);
        //未定义标签
        if (ObjectUtils.isEmpty(labels)) {
            return addLable(map, assetDefine, tableMetadata);
        } else {
            List<Long> labIds = labels.stream().map(item -> item.getLabelId()).collect(Collectors.toList());
            List<LabelDef> allById = labelDefMapper.findAllById(labIds);
            boolean b = allById.stream().noneMatch(item -> "assets".equals(item.getType()) && !ObjectUtils.isEmpty(item.getParam()));
            if (b) {
                return addLable(map, assetDefine, tableMetadata);
            }
            throw new IllegalArgumentException("已经定义资产");
        }
    }

    @Override
    public Boolean unBingDing(Long assestid) {
        try {
            //查询tableid
            AssetDefine assetDefine = assetMapper.findById(assestid).orElseThrow(() -> new IllegalArgumentException("未找到资产"));
            TableMetadata tableMetadata = resourceService.getInfoByResourceId(assetDefine.getResourceId());
            LambdaQueryWrapper<Label> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Label::getTableId, tableMetadata.getId())
                    .eq(Label::getObjId, assestid);
            Label label = labelMapper.findOne(queryWrapper).orElseThrow(() -> new IllegalArgumentException("未绑定资产"));
            labelMapper.deleteById(label.getId());
            labelDefMapper.deleteById(label.getLabelId());
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("删除失败");
        }
        return true;
    }

    @Override
    public List<AssetDefine> queryAssetByUserid(Long userId) {
        //根据userId查询
        LambdaQueryWrapper<LabelDef> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LabelDef::getType, "assets")
                .eq(LabelDef::getName, "binding")
                .eq(LabelDef::getParam, userId);
        List<LabelDef> labelDefs = labelDefMapper.findAll(queryWrapper);
        if (ObjectUtils.isEmpty(labelDefs)) {
            throw new IllegalArgumentException("该用户未绑定资产");
        }
        List<Long> labelDefIds = labelDefs.stream().map(LabelDef::getId).collect(Collectors.toList());
        LambdaQueryWrapper<Label> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Label::getLabelId, labelDefIds);
        List<Label> labels = labelMapper.findAll(lambdaQueryWrapper);
        List<Long> objIds = labels.stream().map(Label::getObjId).collect(Collectors.toList());
        LambdaQueryWrapper<AssetDefine> assetDefineLambdaQueryWrapper = new LambdaQueryWrapper<>();
        assetDefineLambdaQueryWrapper.in(AssetDefine::getId, objIds);
        return assetMapper.findAll(assetDefineLambdaQueryWrapper);
    }

    @Override
    public List<AssetDefine> queryUnBingDing() {
        AssetDefine assetDefine = this.getAssetDef("SYS_ASSET_DEFINE").orElseThrow(() -> new IllegalArgumentException("未定义资产"));
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(assetDefine.getResourceId());
        LambdaQueryWrapper<Label> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Label::getTableId, tableMetadata.getId());
        List<Label> list = labelMapper.findAll(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(list)) {
            List<Long> collect = list.stream().map(item -> item.getLabelId()).collect(Collectors.toList());
            LambdaQueryWrapper<LabelDef> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(LabelDef::getId, collect);
            List<LabelDef> labelDefs = labelDefMapper.findAll(wrapper);
            if (ObjectUtils.isEmpty(labelDefs)) {
                return assetMapper.findAll();
            }
            //已绑定资产的ids
            List<Long> ids = labelDefs.stream()
                    .filter(item -> "assets".equals(item.getType()) && !ObjectUtils.isEmpty(item.getParam()))
                    .map(item -> item.getId())
                    .collect(Collectors.toList());
            lambdaQueryWrapper.clear();
            lambdaQueryWrapper.in(Label::getLabelId, ids);
            //获取已绑定资产的id
            List<Label> bingDing = labelMapper.findAll(lambdaQueryWrapper);
            List<Long> bingDingIds = bingDing.stream().map(item -> item.getObjId()).collect(Collectors.toList());
            LambdaQueryWrapper<AssetDefine> defineLambdaQueryWrapper = new LambdaQueryWrapper<>();
            defineLambdaQueryWrapper.notIn(AssetDefine::getId, bingDingIds);
            return assetMapper.findAll(defineLambdaQueryWrapper);
        }
        return assetMapper.findAll();
    }

    public Map<String, Object> addLable(Map<String, Object> map, AssetDefine assetDefine, TableMetadata tableMetadata) {
        LabelDef labelDef = new LabelDef();
        labelDef.setCategory("isBinding");
        labelDef.setDescribe("资产绑定,参数为资产");
        labelDef.setName("binding");
        labelDef.setType("assets");
        labelDef.setParam(map.get("userId").toString());
        labelDefMapper.insert(labelDef);
        Label label = new Label();
        label.setLabelId(labelDef.getId());
        label.setObjId(assetDefine.getId());
        label.setTableId(tableMetadata.getId());
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("lable", label);
        returnMap.put("lableDef", labelDef);
        return returnMap;
    }

}
