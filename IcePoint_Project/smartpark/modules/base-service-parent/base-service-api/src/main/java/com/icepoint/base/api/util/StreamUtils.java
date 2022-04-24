package com.icepoint.base.api.util;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Stream工具类
 *
 * @author Jiawei
 */
public abstract class StreamUtils {

    private StreamUtils() {}

    /**
     * 根据当前机器的核心状况，如果条件允许的情况，获取一个并行的{@link Stream}
     *
     * @param collection 获取Stream的集合
     * @param <T> 集合中的类型
     * @return 如果当前可用核心大于1，则返回一个并行Stream，否则返回一个串行Stream
     */
    public static <T> Stream<T> parallelStreamIfAvailable(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection))
            return Stream.empty();

        if (Runtime.getRuntime().availableProcessors() > 1)
            return collection.parallelStream();
        else
            return collection.stream();
    }

    public static <T> Stream<T> parallelStreamIfAvailable(Iterator<T> iterator) {
        if (!iterator.hasNext())
            return Stream.empty();

        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED | Spliterator.IMMUTABLE),
                Runtime.getRuntime().availableProcessors() > 1);
    }
}
