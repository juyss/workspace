package com.icepoint.framework.core.io;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.ser.FilterProvider;

import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 针对{@link ObjectWriter}的自定义配置类，所有方法都是基于{@link ObjectWriter}来实现的
 *
 * @author Jiawei Zhao
 */
public class WriterCustomizer {

    private ObjectWriter writer;

    public WriterCustomizer(ObjectWriter writer) {
        this.writer = writer;
    }

    public WriterCustomizer with(SerializationFeature feature) {
        writer = writer.with(feature);
        return this;
    }

    public WriterCustomizer withFeatures(SerializationFeature... features) {
        writer = writer.withFeatures(features);
        return this;
    }

    public WriterCustomizer without(SerializationFeature feature) {
        writer = writer.without(feature);
        return this;
    }

    public WriterCustomizer withoutFeatures(SerializationFeature... features) {
        writer = writer.withoutFeatures(features);
        return this;
    }

    public WriterCustomizer with(JsonGenerator.Feature feature) {
        writer = writer.with(feature);
        return this;
    }

    public WriterCustomizer withFeatures(JsonGenerator.Feature... features) {
        writer = writer.withFeatures(features);
        return this;
    }

    public WriterCustomizer without(JsonGenerator.Feature feature) {
        writer = writer.without(feature);
        return this;
    }

    public WriterCustomizer withoutFeatures(JsonGenerator.Feature... features) {
        writer = writer.withoutFeatures(features);
        return this;
    }

    public WriterCustomizer with(StreamWriteFeature feature) {
        writer = writer.with(feature);
        return this;
    }

    public WriterCustomizer without(StreamWriteFeature feature) {
        writer = writer.without(feature);
        return this;
    }

    public WriterCustomizer with(FormatFeature feature) {
        writer = writer.with(feature);
        return this;
    }

    public WriterCustomizer withFeatures(FormatFeature... features) {
        writer = writer.withFeatures(features);
        return this;
    }

    public WriterCustomizer without(FormatFeature feature) {
        writer = writer.without(feature);
        return this;
    }

    public WriterCustomizer withoutFeatures(FormatFeature... features) {
        writer = writer.withoutFeatures(features);
        return this;
    }

    public WriterCustomizer forType(JavaType rootType) {
        writer = writer.forType(rootType);
        return this;
    }

    public WriterCustomizer forType(Class<?> rootType) {
        writer = writer.forType(rootType);
        return this;
    }

    public WriterCustomizer forType(TypeReference<?> rootType) {
        writer = writer.forType(rootType);
        return this;
    }

    public WriterCustomizer with(DateFormat dateFormat) {
        writer = writer.with(dateFormat);
        return this;
    }

    public WriterCustomizer withDefaultPrettyPrinter() {
        writer = writer.withDefaultPrettyPrinter();
        return this;
    }

    public WriterCustomizer with(PrettyPrinter prettyPrinter) {
        writer = writer.with(prettyPrinter);
        return this;
    }

    public WriterCustomizer with(FilterProvider filterProvider) {
        writer = writer.with(filterProvider);
        return this;
    }

    public WriterCustomizer withRootName(String rootName) {
        writer = writer.withRootName(rootName);
        return this;
    }

    public WriterCustomizer withRootName(PropertyName rootName) {
        writer = writer.withRootName(rootName);
        return this;
    }

    public WriterCustomizer withoutRootName() {
        writer = writer.withoutRootName();
        return this;
    }

    public WriterCustomizer with(FormatSchema formatSchema) {
        writer = writer.with(formatSchema);
        return this;
    }

    public WriterCustomizer withView(Class<?> view) {
        writer = writer.withView(view);
        return this;
    }

    public WriterCustomizer with(Locale locale) {
        writer = writer.with(locale);
        return this;
    }

    public WriterCustomizer with(TimeZone timeZone) {
        writer = writer.with(timeZone);
        return this;
    }

    public WriterCustomizer with(Base64Variant base64Variant) {
        writer = writer.with(base64Variant);
        return this;
    }

    public WriterCustomizer with(CharacterEscapes escapes) {
        writer = writer.with(escapes);
        return this;
    }

    public WriterCustomizer with(JsonFactory jsonFactory) {
        writer = writer.with(jsonFactory);
        return this;
    }

    public WriterCustomizer with(ContextAttributes attributes) {
        writer = writer.with(attributes);
        return this;
    }

    public WriterCustomizer withAttributes(Map<?, ?> attributes) {
        writer = writer.withAttributes(attributes);
        return this;
    }

    public WriterCustomizer withoutAttribute(Object key) {
        writer = writer.withoutAttribute(key);
        return this;
    }

    public WriterCustomizer withRootValueSeparator(String sep) {
        writer = writer.withRootValueSeparator(sep);
        return this;
    }

    public WriterCustomizer withRootValueSeparator(SerializableString sep) {
        writer = writer.withRootValueSeparator(sep);
        return this;
    }

    ObjectWriter getWriter() {
        return writer;
    }
}
