package com.icepoint.framework.core.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.util.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * @author Jiawei Zhao
 */
public abstract class ObjectMapperSerializer<S extends ObjectMapper> extends AbstractStandardSerializer<S> {

    protected ObjectMapperSerializer(S source, SerializationExceptionHandler exceptionHandler) {
        super(source, exceptionHandler);
    }

    /* =========== *
     * 序列化相关方法 *
     * =========== */

    /**
     * 获取一个新创建的{@link ObjectWriter}
     *
     * @return ObjectWriter
     */
    public ObjectWriter getWriter() {
        return getSource().writer();
    }

    /**
     * 将object序列化到OutputStream中，并且可以利用{@link WriterCustomizer}对本次系列化进行自定义配置
     *
     * @param object       要序列化的对象
     * @param outputStream 用来写入数据的OutputStream
     * @param consumer     对本次序列化进行自定义配置的回调函数
     * @param <T>          序列化对象的类型
     * @throws NestedSerializationException 序列化失败时抛出
     */
    public <T> void serialize(T object, OutputStream outputStream, Consumer<WriterCustomizer> consumer)
            throws NestedSerializationException {

        Assert.notNull(consumer, "Consumer不能为空");

        ObjectWriter writer = getWriter();

        WriterCustomizer customizer = new WriterCustomizer(writer);
        consumer.accept(customizer);

        try {
            customizer.getWriter().writeValue(outputStream, object);
        } catch (IOException exception) {
            getExceptionHandler().handle(exception);
        }
    }

    /**
     * 将object序列化成字符串，并且可以利用{@link WriterCustomizer}对本次系列化进行自定义配置
     *
     * @param object   要序列化的对象
     * @param consumer 对本次序列化进行自定义配置的回调函数
     * @param <T>      序列化对象的类型
     * @return 反序列化完成的字符串
     * @throws NestedSerializationException 序列化失败时抛出
     */
    public <T> String serializeAsString(T object, Consumer<WriterCustomizer> consumer)
            throws NestedSerializationException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        serialize(object, outputStream, consumer);
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

    @Override
    public <T> void serializeInternal(T object, OutputStream outputStream) throws IOException {
        getSource().writeValue(outputStream, object);
    }

    /* ============= *
     * 反序列化相关方法 *
     * ============= */

    /**
     * 获取一个新创建的{@link ObjectReader}
     *
     * @return ObjectReader
     */
    public ObjectReader getReader() {
        return getSource().reader();
    }

    /**
     * 将InputStream中的数据反序列化成指定的valueType对象，并且可以利用{@link ReaderCustomizer}来对本次反序列化进行自定义配置
     *
     * @param inputStream InputStream
     * @param valueType   要反序列化的对象的Class类型
     * @param consumer    对本次反序列化进行自定义配置的回调函数
     * @param <T>         解析目标类型
     * @return 反序列化完成的对象
     * @throws NestedSerializationException 反序列化失败时抛出
     */
    public <T> T deserialize(InputStream inputStream, Class<T> valueType, Consumer<ReaderCustomizer> consumer)
            throws NestedSerializationException {

        ReaderCustomizer customizer = customizeReader(consumer);

        try {
            return customizer.getReader().readValue(inputStream, valueType);
        } catch (IOException exception) {
            getExceptionHandler().handle(exception);
            throw new IllegalStateException(SHOULD_NEVER_GET_HERE);
        }
    }

    /**
     * 将InputStream中的数据反序列化成指定的valueType对象，并且可以利用{@link ReaderCustomizer}来对本次反序列化进行自定义配置
     *
     * @param inputStream InputStream
     * @param valueType   要反序列化的对象的TypeReference类型
     * @param consumer    对本次反序列化进行自定义配置的回调函数
     * @param <T>         解析目标类型
     * @return 反序列化完成的对象
     * @throws NestedSerializationException 反序列化失败时抛出
     */
    public <T> T deserialize(InputStream inputStream, TypeReference<T> valueType, Consumer<ReaderCustomizer> consumer)
            throws NestedSerializationException {

        ReaderCustomizer customizer = customizeReader(consumer);

        try {
            ObjectReader reader = customizer.getReader();
            JsonParser parser = reader.getFactory().createParser(inputStream);
            return reader.readValue(parser, valueType);
        } catch (IOException exception) {
            getExceptionHandler().handle(exception);
            throw new IllegalStateException(SHOULD_NEVER_GET_HERE);
        }
    }

    /**
     * 将字符串反序列化成指定的valueType对象，并且可以利用{@link ReaderCustomizer}来对本次反序列化进行自定义配置
     *
     * @param str       要反序列化的字符串
     * @param valueType 要反序列化的对象的Class类型
     * @param consumer  对本次反序列化进行自定义配置的回调函数
     * @param <T>       解析目标类型
     * @return 反序列化完成的对象
     * @throws NestedSerializationException 反序列化失败时抛出
     */
    public <T> T deserialize(String str, Class<T> valueType, Consumer<ReaderCustomizer> consumer)
            throws NestedSerializationException {

        return deserialize(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)), valueType, consumer);
    }

    /**
     * 将字符串反序列化成指定的valueType对象，并且可以利用{@link ReaderCustomizer}来对本次反序列化进行自定义配置
     *
     * @param str       要反序列化的字符串
     * @param valueType 要反序列化的对象的TypeReference类型
     * @param consumer  对本次反序列化进行自定义配置的回调函数
     * @param <T>       解析目标类型
     * @return 反序列化完成的对象
     * @throws NestedSerializationException 反序列化失败时抛出
     */
    public <T> T deserialize(String str, TypeReference<T> valueType, Consumer<ReaderCustomizer> consumer)
            throws NestedSerializationException {

        return deserialize(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)), valueType, consumer);
    }

    @Override
    public <T> T deserializeInternal(InputStream inputStream, Class<T> valueType) throws IOException {
        return getSource().readValue(inputStream, valueType);
    }

    @Override
    public <T> T deserializeInternal(InputStream inputStream, TypeReference<T> valueType) throws IOException {
        return getSource().readValue(inputStream, valueType);
    }

    private ReaderCustomizer customizeReader(Consumer<ReaderCustomizer> consumer) {
        Assert.notNull(consumer, "Consumer不能为空");

        ObjectReader reader = getReader();

        ReaderCustomizer customizer = new ReaderCustomizer(reader);
        consumer.accept(customizer);
        return customizer;
    }
}
