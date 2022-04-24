package com.icepoint.framework.workorder.configuration.agilebpm.base;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.dstz.base.api.context.ICurrentContext;
import com.dstz.base.dao.BaseDao;
import com.dstz.base.dao.baseinterceptor.QueryInterceptor;
import com.dstz.base.dao.baseinterceptor.SaveInterceptor;
import com.dstz.base.db.api.table.DbType;
import com.dstz.base.db.datasource.DataSourceUtil;
import com.dstz.base.db.datasource.DynamicDataSource;
import com.dstz.base.db.transaction.AbDataSourceTransactionManager;
import com.icepoint.framework.autoconfigure.PackageScanConfigurer;
import com.icepoint.framework.autoconfigure.PackageScanRegistry;
import com.icepoint.framework.core.util.Streams;
import lombok.Setter;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

@EnableConfigurationProperties({ AgileBpmDataSourceProperties.class })
@Configuration
public class DataSourceAutoConfiguration implements ResourceLoaderAware , ApplicationContextAware {

    @Setter
    private ResourceLoader resourceLoader;
    @Setter
    private ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactory abSqlSessionFactory(
            DataSource dataSource,
            AgileBpmDataSourceProperties dataSourceProperties,
            ICurrentContext currentContext,
            MybatisPlusProperties properties,
            ObjectProvider<Interceptor[]> interceptorsProvider,
            ObjectProvider<DatabaseIdProvider> databaseIdProviderProvider,
            ObjectProvider<TypeHandler[]> typeHandlersProvider,
            ObjectProvider<LanguageDriver[]> languageDriversProvider,
            ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) throws Exception {

        // TODO 使用 MybatisSqlSessionFactoryBean 而不是 SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(properties.getConfigLocation()));
        }

        applyConfiguration(factory, properties, configurationCustomizersProvider.getIfAvailable());
        if (properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(properties.getConfigurationProperties());
        }
        Interceptor[] interceptors = Stream.concat(
                Streams.stream(interceptorsProvider.getIfAvailable(() -> new Interceptor[0])),
                Streams.stream(saveInterceptor(currentContext), queryInterceptor())
        ).<Interceptor>toArray(Interceptor[]::new);

        if (!ObjectUtils.isEmpty(interceptors)) {
            factory.setPlugins(interceptors);
        }

        DatabaseIdProvider databaseIdProvider = databaseIdProviderProvider.getIfAvailable();
        if (databaseIdProvider != null) {
            factory.setDatabaseIdProvider(databaseIdProvider);
        }
        if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        }
        if (properties.getTypeAliasesSuperType() != null) {
            factory.setTypeAliasesSuperType(properties.getTypeAliasesSuperType());
        }
        if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(properties.getTypeHandlersPackage());
        }

        TypeHandler[] typeHandlers = typeHandlersProvider.getIfAvailable();
        if (!ObjectUtils.isEmpty(typeHandlers)) {
            factory.setTypeHandlers(typeHandlers);
        }

        if (!ObjectUtils.isEmpty(properties.resolveMapperLocations())) {
            factory.setMapperLocations(properties.resolveMapperLocations());
        }

        // TODO 对源码做了一定的修改(因为源码适配了老旧的mybatis版本,但我们不需要适配)
        Class<? extends LanguageDriver> defaultLanguageDriver = properties.getDefaultScriptingLanguageDriver();
        LanguageDriver[] languageDrivers = languageDriversProvider.getIfAvailable();
        if (!ObjectUtils.isEmpty(languageDrivers)) {
            factory.setScriptingLanguageDrivers(languageDrivers);
        }
        Optional.ofNullable(defaultLanguageDriver).ifPresent(factory::setDefaultScriptingLanguageDriver);

        // TODO 自定义枚举包
        if (StringUtils.hasLength(properties.getTypeEnumsPackage())) {
            factory.setTypeEnumsPackage(properties.getTypeEnumsPackage());
        }
        // TODO 此处必为非 NULL
        GlobalConfig globalConfig = properties.getGlobalConfig();
        // TODO 注入填充器
        this.getBeanThen(MetaObjectHandler.class, globalConfig::setMetaObjectHandler);
        // TODO 注入主键生成器
        this.getBeanThen(IKeyGenerator.class, i -> globalConfig.getDbConfig().setKeyGenerator(i));
        // TODO 注入sql注入器
        this.getBeanThen(ISqlInjector.class, globalConfig::setSqlInjector);
        // TODO 注入ID生成器
        this.getBeanThen(IdentifierGenerator.class, globalConfig::setIdentifierGenerator);
        // TODO 设置 GlobalConfig 到 MybatisSqlSessionFactoryBean
        factory.setGlobalConfig(globalConfig);

        factory.setMapperLocations(resolveMapperLocations("classpath*:com/dstz/*/mapping/*.xml", "classpath*:com/dstz/*/*/mapping/*.xml", "classpath:mapper/*.xml"));

        DatabaseIdProvider dip = new VendorDatabaseIdProvider();
        Properties ps = new Properties();
        ps.setProperty("MySQL", "mysql");
        ps.setProperty("Oracle", "oracle");
        dip.setProperties(ps);
        factory.setDatabaseIdProvider(dip);

        addExtensionMappers();

        return factory.getObject();
    }

    private DruidDataSource druidDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
    }

    private DruidDataSource dataSourceDefault(AgileBpmDataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
    }

    @Bean
    public DynamicDataSource dataSource(AgileBpmDataSourceProperties agileBpmDataSourceProperties, DataSourceProperties dataSourceProperties) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(1);
        targetDataSources.put(DataSourceUtil.DEFAULT_DATASOURCE, druidDataSource(dataSourceProperties));
        targetDataSources.put("dataSourceBpm", dataSourceDefault(agileBpmDataSourceProperties));

        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultDbType(DbType.MYSQL.getKey());

        AbDataSourceTransactionManager.dynamicDataSource = dynamicDataSource;
        DataSourceUtil.dynamicDataSource = dynamicDataSource;

        return dynamicDataSource;
    }

    private <T> void getBeanThen(Class<T> clazz, Consumer<T> consumer) {
        if (this.applicationContext.getBeanNamesForType(clazz, false, false).length > 0) {
            consumer.accept(this.applicationContext.getBean(clazz));
        }
    }

//    private PageInterceptor pageInterceptor() {
//        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("autoRuntimeDialect", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        pageInterceptor.setProperties(properties);
//        return pageInterceptor;
//    }

    private QueryInterceptor queryInterceptor() {
        return new QueryInterceptor();
    }

    private SaveInterceptor saveInterceptor(ICurrentContext currentContext) {
        return new SaveInterceptor(currentContext);
    }

    private Resource[] resolveMapperLocations(String... locations) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

        return Streams.stream(locations)
                .map(location -> {
                    try {
                        return resourceResolver.getResources(location);
                    } catch (IOException ignored) {
                        return new Resource[0];
                    }
                })
                .flatMap(Streams::stream)
                .toArray(Resource[]::new);
    }

    // TODO 入参使用 MybatisSqlSessionFactoryBean
    private void applyConfiguration(MybatisSqlSessionFactoryBean factory, MybatisPlusProperties properties, List<ConfigurationCustomizer> configurationCustomizers) {
        // TODO 使用 MybatisConfiguration
        MybatisConfiguration configuration = properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(properties.getConfigLocation())) {
            configuration = new MybatisConfiguration();
        }
        if (configuration != null && !CollectionUtils.isEmpty(configurationCustomizers)) {
            for (ConfigurationCustomizer customizer : configurationCustomizers) {
                customizer.customize(configuration);
            }
        }

        factory.setConfiguration(configuration);
    }

    @Bean
    public BpmMapperRegistry addExtensionMappers() {

        BpmMapperRegistry registry = new BpmMapperRegistry();
        registry.setMarkerInterface(BaseDao.class);
        registry.setBasePackage("com.dstz");

        return registry;
    }

    @Bean(
            name = { "jdbcTemplate" }
    )
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PackageScanConfigurer abPackageScanConfigurer() {
        return new PackageScanConfigurer() {
            @Override
            public void configure(@NonNull PackageScanRegistry registry) {
                registry.addBasePackages("com.dstz");
            }
        };
    }

    @Bean
    public SqlSessionTemplate abSqlSessionTemplate(SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }

}