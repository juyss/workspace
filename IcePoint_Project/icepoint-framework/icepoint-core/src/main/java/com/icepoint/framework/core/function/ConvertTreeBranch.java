package com.icepoint.framework.core.function;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 生成树分支数组
 */
public class ConvertTreeBranch extends AbstractProcessor<ConvertTreeBranch.Parameters, Map<String, Object>> {
    /**
     *
     */
    @Data
    @AllArgsConstructor
    public static class Parameters {
        private Collection<Object> originalList;
        private String idFieldName;
        private String pidFieldName;
        private String childrenFieldName;
    }


    @Override
    public Map<String, Object> processInternal(Parameters parameters) {
        Collection<Object> list = parameters.getOriginalList();
        String idFieldName = parameters.getIdFieldName();
        String pidFieldName = parameters.getPidFieldName();
        String childrenFieldName = parameters.getChildrenFieldName();
        if(ObjectUtils.isEmpty(list) || ObjectUtils.isEmpty(idFieldName) ||
                ObjectUtils.isEmpty(pidFieldName) || ObjectUtils.isEmpty(childrenFieldName)){
            throw new IllegalArgumentException("属性值不能为空");
        }
        List<Object> copy = new ArrayList<>(list);
        list2TreeList(copy, idFieldName, pidFieldName, childrenFieldName);
        Map<String,Object> map = new HashMap<>();
        map.put("nodes",list);
        return map;
    }

    @Override
    protected Map<String, Object> wrapResult(Map<String, Object> result) {
        return null;
    }



     /** 把列表转换为树结构
     *
     * @param originalList      原始list数据
     * @param idFieldName       作为唯一标示的字段名称
     * @param pidFieldName      父节点标识字段名
     * @param childrenFieldName 子节点（列表）标识字段名
     * @return 树结构列表
     */
    public static <T> List<T> list2TreeList(List<T> originalList, String idFieldName, String pidFieldName,
                                            String childrenFieldName) {
        // 获取根节点，即找出父节点为空的对象
        List<T> rootNodeList = new ArrayList<>();
        for (T t : originalList) {
            String parentId = null;
            try {
                parentId = BeanUtils.getProperty(t, pidFieldName);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (StringUtils.isBlank(parentId)) {
                rootNodeList.add(0, t);
            }
        }
        // 将根节点从原始list移除，减少下次处理数据
        originalList.removeAll(rootNodeList);
        // 递归封装树
        try {
            packTree(rootNodeList, originalList, idFieldName, pidFieldName, childrenFieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootNodeList;
    }

    /**
     * 封装树（向下递归）
     *
     * @param parentNodeList    要封装为树的父节点对象集合
     * @param originalList      原始list数据
     * @param keyName           作为唯一标示的字段名称
     * @param pidFieldName      父节点标识字段名
     * @param childrenFieldName 子节点（列表）标识字段名
     */
    private static <T> void packTree(List<T> parentNodeList, List<T> originalList, String keyName,
                                     String pidFieldName, String childrenFieldName) throws Exception {
        for (T parentNode : parentNodeList) {
            // 找到当前父节点的子节点列表
            List<T> children = packChildren(parentNode, originalList, keyName, pidFieldName, childrenFieldName);
            if (children.isEmpty()) {
                continue;
            }
            // 将当前父节点的子节点从原始list移除，减少下次处理数据
            originalList.removeAll(children);
            // 开始下次递归
            packTree(children, originalList, keyName, pidFieldName, childrenFieldName);
        }
    }

    /**
     * 封装子对象
     *
     * @param parentNode        父节点对象
     * @param originalList      原始list数据
     * @param keyName           作为唯一标示的字段名称
     * @param pidFieldName      父节点标识字段名
     * @param childrenFieldName 子节点（列表）标识字段名
     */
    private static <T> List<T> packChildren(T parentNode, List<T> originalList, String keyName, String pidFieldName,
                                            String childrenFieldName) throws Exception {
        // 找到当前父节点下的子节点列表
        List<T> childNodeList = new ArrayList<>();
        String parentId = BeanUtils.getProperty(parentNode, keyName);
        for (T t : originalList) {
            String childNodeParentId = BeanUtils.getProperty(t, pidFieldName);
            if (parentId.equals(childNodeParentId)) {
                childNodeList.add(t);
            }
        }

        // 将当前父节点下的子节点列表写入到当前父节点下（给子节点列表字段赋值）
        if (!childNodeList.isEmpty()) {
            FieldUtils.writeDeclaredField(parentNode, childrenFieldName, childNodeList, true);
        }

        return childNodeList;
    }


}
