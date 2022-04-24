package com.icepoint.base.config.mybatis.pageable;

import com.icepoint.base.config.mybatis.pageable.dialect.MysqlPageableSqlParser;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * 增加Mybatis对pageable的支持
 *
 * @author Jiawei Zhao
 */
public class MybatisPageableSupports {

    @Bean
    public Interceptor pageableInterceptor() {
        return new PageableInterceptor(new MysqlPageableSqlParser());
    }

    @Bean
    public ConfigurationCustomizer pageConfigurationCustomizer() {
        return configuration -> {
            configuration.setObjectFactory(new PageSupportObjectFactory());
        };
    }


}
