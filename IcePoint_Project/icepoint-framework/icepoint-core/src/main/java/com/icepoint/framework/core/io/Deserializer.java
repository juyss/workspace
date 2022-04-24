package com.icepoint.framework.core.io;

import java.io.InputStream;

/**
 * 反序列化器接口
 *
 * @author Jiawei Zhao
 */
public interface Deserializer extends SerializationExceptionHandlerProvider {

    /**
     * 将InputStream中的数据反序列化成指定的valueType对象
     *
     * @param inputStream InputStream
     * @param valueType   要反序列化的对象的Class类型
     * @param <T>         解析目标类型
     * @return 反序列化完成的对象
     * @throws NestedSerializationException 反序列化失败时抛出
     */
    <T> T deserialize(InputStream inputStream, Class<T> valueType) throws NestedSerializationException;
}
