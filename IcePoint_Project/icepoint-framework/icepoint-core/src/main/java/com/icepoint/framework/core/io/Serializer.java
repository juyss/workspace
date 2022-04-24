package com.icepoint.framework.core.io;

import java.io.OutputStream;

/**
 * 序列化器接口
 *
 * @author Jiawei Zhao
 */
public interface Serializer extends SerializationExceptionHandlerProvider {

    /**
     * 将object序列化到OutputStream中
     *
     * @param outputStream 用来写入数据的OutputStream
     * @param object       要序列化的对象
     * @param <T>          序列化对象的类型
     * @throws NestedSerializationException 序列化失败时抛出
     */
    <T> void serialize(OutputStream outputStream, T object) throws NestedSerializationException;

}
