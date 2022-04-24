package com.github.tangyi.core.common.util;

import java.lang.reflect.Array;
import java.text.Collator;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 集合工具
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    /**
     * 中文排序
     */
    private static final Collator CN_COLLATOR = Collator.getInstance(Locale.CHINA);

    /**
     * 获取数组某个值，没有则返回null
     *
     * @param arr
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T get(T[] arr, int index) {
        return CollectionUtils.isNotEmpty(arr) && arr.length > index ? arr[index] : null;
    }

    /**
     * 获取容器某个值，没有则返回null
     *
     * @param list
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T get(List<T> list, int index) {
        return CollectionUtils.isNotEmpty(list) && list.size() > index ? list.get(index) : null;
    }

    /**
     * 是否不为空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 是否为空
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * 是否为空
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }

    /**
     * 是否都为空
     *
     * @param collections
     * @return
     */
    public static boolean isAllEmpty(Collection<?>... collections) {
        if (isNotEmpty(collections)) {
            for (Collection collection : collections) {
                if (isNotEmpty(collection)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 是否都不为空
     *
     * @param collections
     * @return
     */
    public static boolean isAllNotEmpty(Collection<?>... collections) {
        if (isNotEmpty(collections)) {
            for (Collection collection : collections) {
                if (isEmpty(collection)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 初始化List
     *
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> initList(T... elements) {
        if (isEmpty(elements)) {
            return new ArrayList<>(0);
        }

        ArrayList<T> list = new ArrayList<>(elements.length);
        for (T element : elements) {
            list.add(element);
        }

        return list;
    }

    /**
     * 初始化Set
     *
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> HashSet<T> initSet(T... elements) {
        if (isEmpty(elements)) {
            return new HashSet<>(0);
        }

        HashSet<T> set = new HashSet<>(elements.length);
        for (T element : elements) {
            set.add(element);
        }

        return set;
    }

    /**
     * 集合转数组
     *
     * @param collections
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(Collection<T> collections,
                                  Class<T> type) {
        return collections.toArray((T[]) Array.newInstance(type, 0));
    }

    /**
     * 空List
     *
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> emptyList() {
        return initList();
    }

    /**
     * 空Map
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> HashMap<K, V> emptyMap() {
        return new HashMap<>(0);
    }

    /**
     * 截断
     *
     * @param list
     * @param start
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<T> subList(List<T> list,
                                      int start,
                                      int size) {
        if (CheckUtils.isEmpty(list)) {
            return emptyList();
        }

        int length = list.size();
        if (length < start) {
            return emptyList();
        }
        if (length < size) {
            size = length;
        }

        return list.subList(start, size);
    }

    /**
     * @param values 快速生成一个Map.
     * @return
     */
    public static Map<String, Object> newMap(Object... values) {
        int size = values == null || values.length == 0 ? 0 : values.length;
        Map<String, Object> m = new HashMap<>(size);
        if (size == 0) {
            return m;
        }
        for (int i = 0; i < values.length; i = i + 2) {
            m.put(StringUtils.value(values[i]), values[i + 1]);
        }
        return m;
    }

    /**
     * list to group
     *
     * @param list
     * @param classifier
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> LinkedHashMap<K, List<T>> toGroup(Collection<T> list,
                                                           Function<? super T, ? extends K> classifier) {
        if (CheckUtils.isEmpty(list)) {
            return new LinkedHashMap<>(0);
        }
        return list.stream().collect(groupBy(classifier));
    }

    /**
     * groupingBy
     *
     * @param classifier
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> Collector<T, ?, LinkedHashMap<K, List<T>>> groupBy(Function<? super T, ? extends K> classifier) {
        return Collectors.groupingBy(classifier, LinkedHashMap::new, Collectors.toList());
    }

    /**
     * list to map
     *
     * @param list
     * @param classifier
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> LinkedHashMap<K, T> toMap(Collection<T> list,
                                                   Function<? super T, ? extends K> classifier) {
        if (CheckUtils.isEmpty(list)) {
            return new LinkedHashMap<>();
        }
        return list.stream()
                .collect(Collectors.toMap(classifier, a -> a, (a, b) -> a, LinkedHashMap::new));
    }

    /**
     * list to list
     *
     * @param list
     * @param mapper
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> List<K> toList(Collection<T> list,
                                        Function<? super T, ? extends K> mapper) {
        if (CheckUtils.isEmpty(list)) {
            return emptyList();
        }
        return list.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    /**
     * 中文排序
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static int compareCN(Object obj1,
                                Object obj2) {
        return CN_COLLATOR.compare(obj1, obj2);
    }

    /**
     * 交换位置
     *
     * @param list
     * @param index1
     * @param index2
     */
    public static void swap(List list,
                            int index1,
                            int index2) {
        Collections.swap(list, index1, index2);
    }

}
