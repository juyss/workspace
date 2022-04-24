package com.icepoint.framework.data.util;

import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.domain.BaseEntity;
import org.springframework.data.util.Streamable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 方便构造各种Stream以及其中间操作接口的工具类
 *
 * @author Jiawei Zhao
 */
public class EntityStreams {

    private EntityStreams() {
    }

    /**
     * 将entities转换为id的Stream
     *
     * @param entities 实体类Iterable对象
     * @param <T>      实体类类型
     * @param <ID>     实体类id类型
     * @return id的Stream
     */
    public static <T extends BaseEntity<ID>, ID extends Serializable> Stream<ID> idStream(Iterable<T> entities) {
        return Streams.stream(entities).map(BaseEntity::getId);
    }

    /**
     * 将entities转换为id的Streamable
     *
     * @param entities 实体类Iterable对象
     * @param <T>      实体类类型
     * @param <ID>     实体类id类型
     * @return id的Streamable
     */
    public static <T extends BaseEntity<ID>, ID extends Serializable> Streamable<ID> idStreamable(
            Iterable<T> entities) {

        return Streamable.of(entities).map(BaseEntity::getId);
    }

    /**
     * 将entities转换为id的Stream, 并且用提供的Collector进行流结束操作
     *
     * @param entities  实体类Iterable对象
     * @param collector Stream的Collector
     * @param <T>       实体类类型
     * @param <ID>      实体类id类型
     * @param <R>       Collector的返回值类型
     * @return Collector的返回结果
     */
    public static <T extends BaseEntity<ID>, ID extends Serializable, R> R collectIds(Iterable<T> entities,
            Collector<ID, ?, R> collector) {

        return idStream(entities).collect(collector);
    }

    /**
     * 将entities转换为id的List
     *
     * @param entities 实体类Iterable对象
     * @param <T>      实体类类型
     * @param <ID>     实体类id类型
     * @return id的List
     */
    public static <T extends BaseEntity<ID>, ID extends Serializable> List<ID> collectIdList(Iterable<T> entities) {
        return idStream(entities).collect(Collectors.toList());
    }



}
