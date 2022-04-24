package com.icepoint.framework.data.util;

import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.core.util.OrderedComparator;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.domain.Identifiable;
import com.icepoint.framework.data.domain.ParentIdHierarchy;
import com.icepoint.framework.data.domain.TreeNode;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 树型工具类，用来构建树形结构列表
 *
 * @author Jiawei
 */
public final class TreeUtils {

    private TreeUtils() {
    }

    /**
     * 通过一个根节点和获取子节点的函数，来建立树形结构列表
     *
     * @param root        根节点
     * @param getChildren 获取子类的Function，参数是父节点id，返回值是子节点列表
     * @param <T>         实体类类型
     * @param <ID>        id的类型，parentId和id的类型必须一致
     * @return 树形结构列表
     */
    public static <T extends Identifiable<ID> & ParentIdHierarchy<ID>, ID extends Serializable>
    List<TreeNode<T>> buildTreeStructure(T root, Function<ID, List<T>> getChildren) {

        Assert.notNull(getChildren, MessageTemplates.notNull("getChildren函数"));
        Assert.notNull(root, MessageTemplates.notNull("根节点root"));

        List<T> list = new ArrayList<>();
        list.add(root);

        List<T> children = getChildren.apply(root.getId());
        if (!CollectionUtils.isEmpty(children)) {
            list.addAll(children);
            fetchChildrenLoop(children, list, getChildren);
        }

        return buildTreeStructure(list, root.getId());
    }

    private static <T extends Identifiable<ID> & ParentIdHierarchy<ID>, ID extends Serializable>
    void fetchChildrenLoop(List<T> parents, Collection<T> collection, Function<ID, List<T>> getChildren) {

        for (T parent : parents) {
            List<T> children = getChildren.apply(parent.getId());
            if (!CollectionUtils.isEmpty(children)) {
                collection.addAll(children);
                fetchChildrenLoop(children, collection, getChildren);
            }
        }
    }

    /**
     * 根据传入的集合，建立树形结构的{@link TreeNode}列表
     * 要求collection集合中必须包含顶层节点，否则会返回一个空list
     *
     * @param <T>        集合中的元素类型
     * @param collection 要转为树形结构的集合
     * @return 树形结构列表
     */
    public static <T extends Identifiable<ID> & ParentIdHierarchy<ID>, ID extends Serializable>
    List<TreeNode<T>> buildTreeStructure(Collection<T> collection) {
        return buildTreeStructure(collection, null);
    }

    /**
     * 根据传入的集合，建立树形结构的{@link TreeNode}列表，
     * 如果rootId传入null，则要求collection集合中必须包含顶层节点，否则会返回一个空list
     *
     * @param collection 要转为树形结构的集合
     * @param <T>        集合中的元素类型
     * @return 树形结构列表
     */
    public static <T extends Identifiable<ID> & ParentIdHierarchy<ID>, ID extends Serializable>
    List<TreeNode<T>> buildTreeStructure(Collection<T> collection, @Nullable ID rootId) {

        // 如果是空List，则直接返回一个空列表
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }

        Stream<T> stream;
        if (collection.size() == 1) {
            T root = collection.iterator().next();
            if (rootId != null) {
                Assert.isTrue(root.getId().equals(rootId), "rootId与集合数据不相符");
            }
            stream = Streams.stream(root);
        } else if (rootId == null) {
            // 筛选出顶层的节点
            stream = Streams.stream(collection)
                    .filter(item -> item.getParentId() == null);
        } else {
            stream = Streams.stream(collection)
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
     * 将parentStream中的元素转为{@link TreeNode}，并调用{@link #getChildren}建立父子节点关系 <br/>
     * {@link #getChildren}和这个方法会组合成递归调用关系，并最终返回完整的树形结构列表
     *
     * @param collection   所有元素的集合
     * @param parentStream 父节点的{@link Stream}对象
     * @param sort         是否对元素进行排序
     * @param <T>          collection集合中的类型
     * @return 树形结构列表
     */
    @SuppressWarnings("unchecked")
    private static <T extends Identifiable<ID> & ParentIdHierarchy<ID>, ID extends Serializable>
    List<TreeNode<T>> mapToTreeNodes(Collection<T> collection, Stream<T> parentStream, boolean sort) {

        if (sort) {
            parentStream = parentStream.sorted(OrderedComparator.INSTANCE);
        }

        return parentStream.map(item -> {
            TreeNode<T> tree = new TreeNode<>((Class<T>) item.getClass());
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
    private static <T extends Identifiable<ID> & ParentIdHierarchy<ID>, ID extends Serializable>
    List<TreeNode<T>> getChildren(Object parentId, Collection<T> collection) {

        Stream<T> stream = Streams.stream(collection)
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

            T element = null;
            Iterator<T> it = collection.iterator();
            while (it.hasNext() && element == null) {
                element = it.next();
                if (element != null) {
                    genericType = element.getClass();
                }
            }
        }

        return genericType == null ? Object.class : genericType;
    }


}
