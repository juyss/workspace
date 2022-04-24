package com.icepoint.framework.core.io;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * {@link StandardSerializer}的一个基础实现抽象类，主要是做了对{@link IOException}的处理
 *
 * @author Jiawei Zhao
 */
public abstract class AbstractStandardSerializer<S> implements StandardSerializer<S> {

    protected static final String SHOULD_NEVER_GET_HERE = "理论上不会运行到这一行";

    private final S source;

    private final SerializationExceptionHandler exceptionHandler;

    protected AbstractStandardSerializer(S source, SerializationExceptionHandler exceptionHandler) {

        Assert.notNull(source, "delegate不能为空");
        Assert.notNull(exceptionHandler, "exceptionHandler不能为空");

        this.source = source;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public S getSource() {
        return source;
    }

    @Override
    public SerializationExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    @Override
    public final <T> void serialize(OutputStream outputStream, T object) throws NestedSerializationException {
        try {
            serializeInternal(object, outputStream);
        } catch (IOException exception) {
            exceptionHandler.handle(exception);
        }
    }

    /**
     * 一个委托方法，让子类实现具体的序列化操作
     *
     * @param object       要序列化的对象
     * @param outputStream 要写入数据的OutputStream
     * @param <T>          序列化对象的类型
     * @throws IOException 当序列化失败时
     */
    protected abstract <T> void serializeInternal(T object, OutputStream outputStream) throws IOException;

    @Override
    public final <T> T deserialize(InputStream inputStream, Class<T> valueType) throws NestedSerializationException {
        try {
            return deserializeInternal(inputStream, valueType);
        } catch (IOException exception) {
            exceptionHandler.handle(exception);
            throw new IllegalStateException(SHOULD_NEVER_GET_HERE);
        }
    }

    /**
     * 一个委托方法，让子类实现具体的反序列化操作
     *
     * @param inputStream 读取数据的InputStream
     * @param valueType   要反序列化的目标对象Class类型
     * @param <T>         反序列化的对象类型
     * @return 反序列化完成的对象
     * @throws IOException 当反序列化失败时
     */
    protected abstract <T> T deserializeInternal(InputStream inputStream, Class<T> valueType) throws IOException;

    @Override
    public <T> T deserialize(InputStream inputStream, TypeReference<T> valueType) throws NestedSerializationException {
        try {
            return deserializeInternal(inputStream, valueType);
        } catch (IOException exception) {
            exceptionHandler.handle(exception);
            throw new IllegalStateException(SHOULD_NEVER_GET_HERE);
        }
    }

    protected abstract <T> T deserializeInternal(InputStream inputStream, TypeReference<T> valueType)
            throws IOException;

}
