package com.icepoint.framework.core.io;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 标准序列化器，包含序列化和反序列化的方法
 * 增加了字符串的序列化与反序列化方法
 *
 * @author Jiawei Zhao
 */
public interface StandardSerializer<S> extends Serializer, Deserializer {

    /**
     * 获取真正处理序列化和反序列化的对象
     *
     * @return S
     */
    S getSource();

    /**
     * 把对象序列化成字符串
     *
     * @param object 要序列化的对象
     * @param <T>    序列化对象的类型
     * @return 序列化完成的String
     * @throws NestedSerializationException 序列化失败时抛出
     */
    default <T> String serializeAsString(T object) throws NestedSerializationException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        serialize(outputStream, object);
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

    /**
     * 将InputStream中的数据反序列化成指定的valueType对象
     *
     * @param inputStream InputStream
     * @param valueType   要反序列化的对象的TypeReference类型
     * @param <T>         解析目标类型
     * @return 反序列化完成的对象
     * @throws NestedSerializationException 反序列化失败时抛出
     */
    <T> T deserialize(InputStream inputStream, TypeReference<T> valueType) throws NestedSerializationException;

    /**
     * 将字符串反序列化成指定的valueType对象
     *
     * @param str       要反序列化的字符串
     * @param valueType 要反序列化的对象的Class类型
     * @param <T>       解析目标类型
     * @return 反序列化完成的对象
     * @throws NestedSerializationException 反序列化失败时抛出
     */
    default <T> T deserialize(String str, Class<T> valueType) throws NestedSerializationException {
        return deserialize(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)), valueType);
    }

    /**
     * 将字符串反序列化成指定的valueType对象
     *
     * @param str       要反序列化的字符串
     * @param valueType 要反序列化的对象的TypeReference类型
     * @param <T>       解析目标类型
     * @return 反序列化完成的对象
     * @throws NestedSerializationException 反序列化失败时抛出
     */
    default <T> T deserialize(String str, TypeReference<T> valueType) throws NestedSerializationException {
        return deserialize(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)), valueType);
    }

}
