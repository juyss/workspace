package com.icepoint.base.api.util;

import com.icepoint.base.api.domain.Identifiable;
import com.icepoint.base.api.domain.ParentIdHierarchy;
import com.icepoint.base.api.domain.TreeEntity;
import com.icepoint.base.api.supports.PropertyComparators;
import lombok.experimental.UtilityClass;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 树型实体工具类
 *
 * @author Jiawei
 */
@UtilityClass
public class TreeEntityUtils {

    /**
     * 根据传入的集合，建立树形结构的{@link TreeEntity}列表
     * 要求collection集合中必须包含顶层节点，否则会返回一个空list
     *
     * @param collection 要转为树形结构的集合
     * @param <T>        集合中的元素类型
     * @return 树形结构列表
     */
    public static <T extends Identifiable<?> & ParentIdHierarchy>
    List<TreeEntity<T>> buildTreeStructure(Collection<T> collection) {
        return buildTreeStructure(collection, null);
    }

    /**
     * 根据传入的集合，建立树形结构的{@link TreeEntity}列表，
     * 如果rootId传入null，则要求collection集合中必须包含顶层节点，否则会返回一个空list
     *
     * @param collection 要转为树形结构的集合
     * @param <T>        集合中的元素类型
     * @return 树形结构列表
     */
    public static <T extends Identifiable<?> & ParentIdHierarchy>
    List<TreeEntity<T>> buildTreeStructure(Collection<T> collection, @Nullable Long rootId) {
        // 如果是空List，则直接返回一个空列表
        if (CollectionUtils.isEmpty(collection))
            return Collections.emptyList();

        Stream<T> stream;
        if (rootId == null) {
            // 筛选出顶层的节点
            stream = StreamUtils.parallelStreamIfAvailable(collection)
                    .filter(item -> null == item.getParentId() || 0 == item.getParentId());
        } else {
            stream = StreamUtils.parallelStreamIfAvailable(collection)
                    .filter(item -> Objects.equals(item.getId(), rootId));
        }

        // 将collection转为树形结构列表
        return mapToTreeNodes(
                collection,
                stream,
                Ordered.class.isAssignableFrom(getCollectionGenericType(collection))
        );
    }

    /**
     * 将parentStream中的元素转为{@link TreeEntity}，并调用{@link #getChildren}建立父子节点关系 <br/>
     * {@link #getChildren}和这个方法会组合成递归调用关系，并最终返回完整的树形结构列表
     *
     * @param collection   所有元素的集合
     * @param parentStream 父节点的{@link Stream}对象
     * @param ordered      是否对元素进行排序
     * @param <T>          collection集合中的类型
     * @return 树形结构列表
     */
    private static <T extends Identifiable<?> & ParentIdHierarchy>
    List<TreeEntity<T>> mapToTreeNodes(Collection<T> collection, Stream<T> parentStream, boolean ordered) {
        if (ordered)
            parentStream = parentStream.sorted(PropertyComparators.ORDERED);

        return parentStream.map(item -> {
            TreeEntity<T> tree = new TreeEntity<>();
            tree.setEntity(item);
            tree.setChildren(getChildren(item.getId(), collection));
            return tree;
        }).collect(Collectors.toList());
    }

    /**
     * 根据传入parentId，在collection集合中寻找所有对应的子节点，建立父子节点关系 <br/>
     * {@link #mapToTreeNodes}和这个方法会组合成递归调用关系，并最终返回一个完整树形结构列表
     *
     * @param parentId   父节点id
     * @param collection 全部元素的集合
     * @param <T>        collection集合中的类型
     * @return 树形结构列表
     */
    private static <T extends Identifiable<?> & ParentIdHierarchy>
    List<TreeEntity<T>> getChildren(Object parentId, Collection<T> collection) {
        Stream<T> stream = StreamUtils.parallelStreamIfAvailable(collection)
                .filter(item -> Objects.equals(parentId, item.getParentId()));

        return mapToTreeNodes(
                collection,
                stream,
                Ordered.class.isAssignableFrom(getCollectionGenericType(collection))
        );
    }

    /**
     * 获取collection中的泛型类型，如果获取失败，返回{@link Class<Object>}
     *
     * @param collection 要获取泛型的集合
     * @param <T>        collection集合中的类型
     * @return collection中的泛型Class对象
     */
    private static <T> Class<?> getCollectionGenericType(Collection<T> collection) {
        Class<?> genericType = null;
        if (!CollectionUtils.isEmpty(collection)) {
            T firstElement = collection.iterator().next();
            genericType = firstElement == null ? null : firstElement.getClass();
        }

        return genericType == null ? Object.class : genericType;
    }
}
