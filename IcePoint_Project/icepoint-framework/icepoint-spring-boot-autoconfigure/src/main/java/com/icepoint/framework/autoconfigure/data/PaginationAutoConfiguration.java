package com.icepoint.framework.autoconfigure.data;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import com.icepoint.framework.core.util.FieldUtils;
import com.icepoint.framework.data.mybatis.pagination.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 分页自动配置
 *
 * @author Jiawei Zhao
 */
@ConditionalOnClass({ SqlSession.class, Pageable.class })
@Configuration(proxyBeanMethods = false)
public class PaginationAutoConfiguration {

    private List<AbstractPaginationInterceptor> interceptors;

    @Autowired
    public void setInterceptors(List<AbstractPaginationInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Bean
    public PaginationAdapterInterceptor paginationAdapterInterceptor() {
        return new PaginationAdapterInterceptor(interceptors);
    }

    /**
     * 配置Pageable的分页拦截器
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({
            SqlSession.class, Pageable.class
    })
    public static class PageableSupportConfiguration {

        @Order(Ordered.HIGHEST_PRECEDENCE)
        @Bean
        public PageableQueryInterceptor pageableQueryInterceptor() {
            return new PageableQueryInterceptor();
        }
    }

    /**
     * 配置PageHelper的分页拦截器
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({
            SqlSession.class, Pageable.class, PageHelper.class, PageInterceptor.class
    })
    public static class PageHelperSupportAutoConfiguration {

        @Bean
        public PageHelperSupportInterceptor pageHelperSupportInterceptor() {
            return new PageHelperSupportInterceptor();
        }

        /**
         * 为了适配PageHelperAutoConfiguration的配置
         */
        @EnableConfigurationProperties(PageHelperProperties.class)
        @ConditionalOnClass({
                SqlSession.class, Pageable.class, PageHelper.class,
                PageInterceptor.class, PageHelperAutoConfiguration.class
        })
        @Configuration
        @AutoConfigureAfter(PageHelperAutoConfiguration.class)
        public static class PageHelperAutoConfigurationAdaptingConfiguration {

            private List<SqlSessionFactory> sqlSessionFactoryList;

            private PaginationAdapterInterceptor paginationAdapterInterceptor;

            @Autowired
            public void setSqlSessionFactoryList(List<SqlSessionFactory> sqlSessionFactoryList) {
                this.sqlSessionFactoryList = sqlSessionFactoryList;
            }

            @Autowired
            public void setPaginationAdapterInterceptor(PaginationAdapterInterceptor paginationAdapterInterceptor) {
                this.paginationAdapterInterceptor = paginationAdapterInterceptor;
            }

            @PostConstruct
            public void replacePageInterceptor() {

                for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {

                    org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
                    List<Interceptor> interceptors = FieldUtils.getField(configuration, "interceptorChain.interceptors");

                    if (!CollectionUtils.isEmpty(interceptors)) {
                        interceptors.removeIf(interceptor -> interceptor.getClass() == PageInterceptor.class);
                        interceptors.add(paginationAdapterInterceptor);
                    }
                }
            }
        }
    }

    /**
     * 配置MybatisPlus的分页拦截器
     */
    @ConditionalOnClass({
            SqlSession.class, Pageable.class, PaginationInterceptor.class
    })
    @Configuration(proxyBeanMethods = false)
    public static class MybatisPlusSupportAutoConfiguration {

        @Bean
        public MybatisPlusSupportInterceptor mybatisPlusSupportInterceptor() {
            return new MybatisPlusSupportInterceptor();
        }
    }
}
