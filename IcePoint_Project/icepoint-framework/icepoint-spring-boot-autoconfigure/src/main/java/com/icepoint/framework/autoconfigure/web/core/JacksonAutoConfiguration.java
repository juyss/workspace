package com.icepoint.framework.autoconfigure.web.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.icepoint.framework.core.util.Streams;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Jackson配置类
 *
 * @author Jiawei Zhao
 */
@Configuration(proxyBeanMethods = false)
public class JacksonAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(ObjectProvider<Module> modules) {
        return builder -> builder
                .failOnEmptyBeans(false)
                .failOnUnknownProperties(false)
                .serializationInclusion(JsonInclude.Include.ALWAYS)
                .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(long.class, ToStringSerializer.instance)
                .modules(Streams.streamable(modules).toList());
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(Hibernate5Module.class)
    public static class HibernateJacksonConfiguration {

        /**
         * 因为Hibernate的实体是代理类，所以反序列化时需要做特殊处理，这里是配置
         *
         * @return Hibernate5Module
         */
        @ConditionalOnMissingBean
        @Bean
        public Hibernate5Module hibernate5Module() {
            return new Hibernate5Module()
                    .enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING)
                    .enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        }
    }

    @ConditionalOnClass(XmlMapper.class)
    @Configuration(proxyBeanMethods = false)
    public static class XmlMapperConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public XmlMapper xmlMapper(Jackson2ObjectMapperBuilder builder) {
            return builder.createXmlMapper(true).build();
        }
    }
}
