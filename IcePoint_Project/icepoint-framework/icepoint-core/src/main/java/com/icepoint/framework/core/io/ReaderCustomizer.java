package com.icepoint.framework.core.io;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 针对{@link ObjectReader}的自定义配置类，所有方法都是基于{@link ObjectReader}来实现的
 *
 * @author Jiawei Zhao
 */
public class ReaderCustomizer {

    private ObjectReader reader;

    public ReaderCustomizer(ObjectReader reader) {
        this.reader = reader;
    }

    public ReaderCustomizer with(DeserializationFeature feature) {
        reader = reader.with(feature);
        return this;
    }

    public ReaderCustomizer withFeatures(DeserializationFeature... features) {
        reader = reader.withFeatures(features);
        return this;
    }

    public ReaderCustomizer without(DeserializationFeature feature) {
        reader = reader.without(feature);
        return this;
    }

    public ReaderCustomizer withoutFeatures(DeserializationFeature... features) {
        reader = reader.withoutFeatures(features);
        return this;
    }

    public ReaderCustomizer with(JsonParser.Feature feature) {
        reader = reader.with(feature);
        return this;
    }

    public ReaderCustomizer withFeatures(JsonParser.Feature... features) {
        reader = reader.withFeatures(features);
        return this;
    }

    public ReaderCustomizer without(JsonParser.Feature feature) {
        reader = reader.without(feature);
        return this;
    }

    public ReaderCustomizer withoutFeatures(JsonParser.Feature... features) {
        reader = reader.withoutFeatures(features);
        return this;
    }

    public ReaderCustomizer with(StreamReadFeature feature) {
        reader = reader.with(feature);
        return this;
    }

    public ReaderCustomizer without(StreamReadFeature feature) {
        reader = reader.without(feature);
        return this;
    }

    public ReaderCustomizer with(FormatFeature feature) {
        reader = reader.with(feature);
        return this;
    }

    public ReaderCustomizer withFeatures(FormatFeature... features) {
        reader = reader.withFeatures(features);
        return this;
    }

    public ReaderCustomizer without(FormatFeature feature) {
        reader = reader.without(feature);
        return this;
    }

    public ReaderCustomizer withoutFeatures(FormatFeature... features) {
        reader = reader.withoutFeatures(features);
        return this;
    }

    public ReaderCustomizer at(final String pointerExpr) {
        reader = reader.at(pointerExpr);
        return this;
    }

    public ReaderCustomizer at(final JsonPointer pointer) {
        reader = reader.at(pointer);
        return this;
    }

    public ReaderCustomizer with(DeserializationConfig config) {
        reader = reader.with(config);
        return this;
    }

    public ReaderCustomizer with(JsonNodeFactory factory) {
        reader = reader.with(factory);
        return this;
    }

    public ReaderCustomizer with(JsonFactory factory) {
        reader = reader.with(factory);
        return this;
    }

    public ReaderCustomizer withRootName(String rootName) {
        reader = reader.withRootName(rootName);
        return this;
    }

    public ReaderCustomizer withRootName(PropertyName rootName) {
        reader = reader.withRootName(rootName);
        return this;
    }

    public ReaderCustomizer withoutRootName() {
        reader = reader.withoutRootName();
        return this;
    }

    public ReaderCustomizer forType(JavaType valueType) {
        reader = reader.forType(valueType);
        return this;
    }

    public ReaderCustomizer forType(Class<?> valueType) {
        reader = reader.forType(valueType);
        return this;
    }

    public ReaderCustomizer forType(TypeReference<?> valueType) {
        reader = reader.forType(valueType);
        return this;
    }

    public ReaderCustomizer withValueToUpdate(Object value) {
        reader = reader.withValueToUpdate(value);
        return this;
    }

    public ReaderCustomizer withView(Class<?> view) {
        reader = reader.withView(view);
        return this;
    }

    public ReaderCustomizer with(Locale locale) {
        reader = reader.with(locale);
        return this;
    }

    public ReaderCustomizer with(TimeZone timeZone) {
        reader = reader.with(timeZone);
        return this;
    }

    public ReaderCustomizer withHandler(DeserializationProblemHandler handler) {
        reader = reader.withHandler(handler);
        return this;
    }

    public ReaderCustomizer with(Base64Variant base64Variant) {
        reader = reader.with(base64Variant);
        return this;
    }

    public ReaderCustomizer withFormatDetection(ObjectReader... readers) {
        reader = reader.withFormatDetection(readers);
        return this;
    }

    public ReaderCustomizer withFormatDetection(DataFormatReaders readers) {
        reader = reader.withFormatDetection(readers);
        return this;
    }

    public ReaderCustomizer with(InjectableValues injectableValues) {
        reader = reader.with(injectableValues);
        return this;
    }

    public ReaderCustomizer with(ContextAttributes attributes) {
        reader = reader.with(attributes);
        return this;
    }

    public ReaderCustomizer withAttributes(Map<?, ?> attributes) {
        reader = reader.withAttributes(attributes);
        return this;
    }

    public ReaderCustomizer withoutAttribute(Object key) {
        reader = reader.withoutAttribute(key);
        return this;
    }

    ObjectReader getReader() {
        return reader;
    }
}
