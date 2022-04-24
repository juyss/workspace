package com.github.tangyi.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author gaokx
 * @Description long 类型转换到前端精度丢失统一处理
 * @create 2020-10-24 19:24
 **/

@Configuration
public class LongToStringJsonConfig implements WebMvcConfigurer {

  /**
   * 前端js处理大数字丢失精度问题，将Long和BigInteger转换成string
   * @param converters
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
    simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
    simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
    objectMapper.registerModule(simpleModule);
    jackson2HttpMessageConverter.setObjectMapper(objectMapper);
    converters.add(jackson2HttpMessageConverter);
  }
}
