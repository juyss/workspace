package com.github.tangyi.config;


import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.core.mybatis.service.impl.CommonDaoServiceImpl;
import com.github.tangyi.core.mybatis.mapper.CommonDaoHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis配置
 *
 * @author hedongzhou
 * @date 2018/06/14
 */
@Configuration
public class MyBatisAutoConfiguration {

    /**
     * 数据库基础操作
     *
     * @return
     */
    @Bean
    @Qualifier("commonDaoMapperFactory")
    @ConditionalOnMissingBean
    public CommonDaoHelper commonDaoMapperFactory() {
        return new CommonDaoHelper();
    }

    /**
     * 数据库基础操作
     *
     * @return
     */
    @Bean
    @Qualifier("commonDaoService")
    @ConditionalOnMissingBean
    public CommonDaoService commonDaoService() {
        return new CommonDaoServiceImpl();
    }

}
