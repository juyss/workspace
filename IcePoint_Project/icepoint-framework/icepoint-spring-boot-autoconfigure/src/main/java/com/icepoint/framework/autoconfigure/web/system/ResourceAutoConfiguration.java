package com.icepoint.framework.autoconfigure.web.system;

import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.system.dao.SpecificTableMapper;
import com.icepoint.framework.web.system.expression.ExpressionContext;
import com.icepoint.framework.web.system.resource.DefaultResourceModelViewResolver;
import com.icepoint.framework.web.system.resource.ResourceModelConverter;
import com.icepoint.framework.web.system.resource.ResourceModelViewResolver;
import com.icepoint.framework.web.system.resource.parser.*;
import com.icepoint.framework.web.system.resource.query.GenericTableResourceModelConverter;
import com.icepoint.framework.web.system.resource.source.*;
import com.icepoint.framework.web.system.service.GenericDataSequenceService;
import com.icepoint.framework.web.system.service.GenericDataService;
import com.icepoint.framework.web.system.service.ResourceMetadataService;
import com.icepoint.framework.web.system.support.LogicalResourceAccessProcessor;
import com.icepoint.framework.web.system.support.LookupHandlerMethodArgumentResolver;
import com.icepoint.framework.web.system.support.ResourceModelHandlerMethodArgumentResolver;
import com.icepoint.framework.web.system.support.ResourceModelMetadataHandlerMethodArgumentResolver;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
@Configuration(proxyBeanMethods = false)
public class ResourceAutoConfiguration implements WebMvcConfigurer {

    private final Lazy<LookupHandlerMethodArgumentResolver> lookupHandlerMethodArgumentResolver;
    private final Lazy<ResourceModelMetadataHandlerMethodArgumentResolver> resourceModelMetadataHandlerMethodArgumentResolver;
    private final Lazy<ResourceModelHandlerMethodArgumentResolver> resourceModelHandlerMethodArgumentResolver;

    public ResourceAutoConfiguration(ApplicationContext context) {
        this.lookupHandlerMethodArgumentResolver = Lazy.of(() -> context
                .getBean(LookupHandlerMethodArgumentResolver.class));
        this.resourceModelMetadataHandlerMethodArgumentResolver = Lazy.of(() -> context
                .getBean(ResourceModelMetadataHandlerMethodArgumentResolver.class));
        this.resourceModelHandlerMethodArgumentResolver = Lazy.of(() -> context
                .getBean(ResourceModelHandlerMethodArgumentResolver.class));
    }

    @ConditionalOnMissingBean
    @Bean
    public OperationSqlParser operationSqlParser() {
        return new DefaultOperationSqlParser();
    }

    @ConditionalOnMissingBean
    @Bean
    public QuerySqlParser querySqlParser(OperationSqlParser operationSqlParser) {
        return new MybatisMapperQuerySqlParser(operationSqlParser);
    }

    @ConditionalOnMissingBean
    @Bean
    public SpecificTableSqlParser specificTableSqlParser() {
        return new MysqlSpecificTableSqlParser();
    }

    @ConditionalOnClass(User.class)
    @Configuration(proxyBeanMethods = false)
    static class SecurityAdaptAutoConfiguration {

        @ConditionalOnMissingBean
        @Bean
        public SecurityResourceAccessProcessor securityResourceAccessProcessor() {
            return new SecurityResourceAccessProcessor();
        }

    }

    @ConditionalOnMissingBean
    @Bean
    public GenericTableResourceModelConverter genericTableResourceModelConverter() {
        return new GenericTableResourceModelConverter();
    }

    @ConditionalOnMissingBean
    @Bean
    public ResourceDataSourceAdapter resourceDataSourceAdapter(ObjectProvider<ResourceDataSource> dataSources) {
        TableTypeResourceDataSourceAdapter adapter = new TableTypeResourceDataSourceAdapter();
        dataSources.forEach(adapter::add);
        return adapter;
    }

    @ConditionalOnMissingBean
    @Bean
    public ResourceModelViewResolver resourceModelViewResolver() {
        return new DefaultResourceModelViewResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(lookupHandlerMethodArgumentResolver.get());
        resolvers.add(resourceModelMetadataHandlerMethodArgumentResolver.get());
        resolvers.add(resourceModelHandlerMethodArgumentResolver.get());
    }

    @Bean
    public LookupHandlerMethodArgumentResolver lookupHandlerMethodArgumentResolver(
            ResourceModelMetadataHandlerMethodArgumentResolver metadataResolver,
            ExpressionContext expressionContext) {
        return new LookupHandlerMethodArgumentResolver(metadataResolver, expressionContext);
    }

    @Bean
    public ResourceModelMetadataHandlerMethodArgumentResolver resourceModelMetadataHandlerMethodArgumentResolver(
            ResourceMetadataService service) {

        return new ResourceModelMetadataHandlerMethodArgumentResolver(service);
    }

    @Bean
    public ResourceModelHandlerMethodArgumentResolver resourceModelHandlerMethodArgumentResolver(
            LookupHandlerMethodArgumentResolver lookupResolver,
            ResourceModelConverter resourceModelConverter) {
        return new ResourceModelHandlerMethodArgumentResolver(lookupResolver, resourceModelConverter);
    }

    @Bean
    public GenericTableResourceDataSource genericTableResourceDataSource(
            GenericTableResourceModelConverter converter,
            GenericDataService dataService,
            GenericDataSequenceService sequenceService) {
        return new GenericTableResourceDataSource(converter, dataService, sequenceService);
    }

    @Bean
    public SpecificTableResourceDataSource specificTableResourceDataSource(
            SpecificTableMapper mapper,
            SpecificTableSqlParser sqlParser,
            ResourceModelConverter converter) {
        return new SpecificTableResourceDataSource(mapper, sqlParser, converter);
    }

    @Bean
    public LogicalResourceAccessProcessor logicalResourceAccessProcessor() {
        return new LogicalResourceAccessProcessor();
    }
}
