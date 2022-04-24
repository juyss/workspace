package com.icepoint.framework.core.util;

import org.springframework.data.util.Streamable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Stream工具类
 *
 * @author Jiawei Zhao
 */
public class Streams {

    private static final boolean DEFAULT_PARALLEL = false;

    private Streams() {
    }

    /**
     * 将Iterable转换为Stream
     *
     * @param iterable Iterable对象
     * @param <T>      Iterable的元素类型
     * @return Stream对象
     */
    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), canParallel());
    }

    public static <T> Stream<T> stream(Collection<T> collection) {
        return StreamSupport.stream(collection.spliterator(), canParallel());
    }

    @SafeVarargs
    public static <T> Stream<T> stream(T... items) {
        return StreamSupport.stream(Arrays.spliterator(items, 0, items.length), canParallel());
    }

    public static <T> Streamable<T> streamable(Iterable<T> iterable) {
        return Streamable.of(() -> stream(iterable));
    }

    public static <T> Streamable<T> streamable(Collection<T> collection) {
        return Streamable.of(() -> stream(collection));
    }

    @SafeVarargs
    public static <T> Streamable<T> streamable(T... items) {
        return Streamable.of(() -> stream(items));
    }

    /**
     * 根据mapper提供的返回值，对Stream元素进行去重，如果mapper的返回值为null，则直接抛弃该元素
     * 在Stream的filter操作中使用。
     * <p>
     * 注意: 提供的返回值类型必须重写equals和hashCode才有效果
     *
     * @param mapper 提供去重元素的mapper
     * @param <T>    Stream内元素类型
     * @return 用来去重的Predicate
     */
    public static <T> Predicate<T> distinct(Function<T, ?> mapper) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> {
            Object result = mapper.apply(t);
            return result != null && seen.add(result);
        };
    }

    /**
     * 根据环境判断是否构建并行流
     *
     * @return 如果可以构建并行流返回true, 否则返回false
     */
    private static boolean canParallel() {
        return DEFAULT_PARALLEL; // TODO: 暂时默认返回false, 后面再看怎么处理
    }
}
