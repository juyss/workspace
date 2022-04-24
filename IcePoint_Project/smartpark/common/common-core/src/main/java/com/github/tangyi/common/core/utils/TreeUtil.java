package com.github.tangyi.common.core.utils;

import com.github.tangyi.common.core.persistence.TreeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.util.CollectionUtils;

/**
 * @author tangyi
 * @date 2018-10-01 15:38
 */
public class TreeUtil {

    /**
     * 两层循环实现建树
     *
     * @param treeEntities 传入的树实体列表
     * @return List
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> List<T> buildTree(List<? extends TreeEntity<T>> treeEntities, Object root) {
        List<TreeEntity<T>> treeEntityArrayList = new ArrayList<>();
        treeEntities.forEach(treeEntity -> {
            if (treeEntity.getParentId().equals(root)){
                treeEntityArrayList.add(treeEntity);
            }
            treeEntities.forEach(childTreeEntity -> {
                if (childTreeEntity.getParentId().equals(treeEntity.getId())) {
                    if (treeEntity.getChildren() == null){
                        treeEntity.setChildren(new ArrayList<>());
                    }
                    treeEntity.add(childTreeEntity);
                }
            });
        });
        return (List<T>) treeEntityArrayList;
    }

    /**
     * 获取当前树下的子树List
     * @param treeEntities
     * @param id
     * @param selfFlag 是否包含自身
     * @return
     * @author gaokx
     */
    public static  List<TreeEntity> getChildrenList(List<? extends TreeEntity> treeEntities, Object id, boolean selfFlag) {
        List<TreeEntity> treeList = new ArrayList<>();
        treeEntities.forEach(entity -> {
            //是否包含自身
            Object compareId = selfFlag ? entity.getId() : entity.getParentId() ;
            if(Objects.equals(id,compareId)){
                treeList.add(entity);
                if(!CollectionUtils.isEmpty(entity.getChildren())){
                    List childrenList = treeToList(entity.getChildren());
                    treeList.addAll(childrenList);
                }
            }else{
                if (!CollectionUtils.isEmpty(entity.getChildren())) {
                    List childrenList = getChildrenList(entity.getChildren(), id, selfFlag);
                    treeList.addAll(childrenList);
                }
            }
        });
        return treeList;
    }

    /**
     * tree 转list
     * @param treeEntities
     * @return
     */
    public static  <T>  List treeToList(List<? extends TreeEntity<T>>  treeEntities) {
        List<TreeEntity> result = new ArrayList<>();
        for (TreeEntity entity : treeEntities) {
            result.add(entity);
            List<TreeEntity<T>> children = entity.getChildren();
            if (children != null && children.size() > 0) {
                List<TreeEntity> entityList = treeToList(children);
                result.addAll(entityList);
            }
        }
        if (result.size() > 0) {
            for (TreeEntity entity : result) {
                entity.setChildren(null);
            }
        }
        return result;
    }

}
