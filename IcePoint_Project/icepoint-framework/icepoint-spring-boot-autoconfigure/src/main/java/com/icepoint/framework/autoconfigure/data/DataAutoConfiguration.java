package com.icepoint.framework.autoconfigure.data;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisMapperRegistry;
import com.icepoint.framework.autoconfigure.IcepointFrameworkBaseConfiguration;
import com.icepoint.framework.autoconfigure.PackageScanRegistry;
import com.icepoint.framework.core.util.EnvironmentUtils;
import com.icepoint.framework.core.util.FieldUtils;
import com.icepoint.framework.data.dao.StdRepositoryImpl;
import com.icepoint.framework.data.domain.BaseEntity;
import com.icepoint.framework.data.mybatis.MapperRegistry;
import com.icepoint.framework.web.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;

/**
 * @author Jiawei Zhao
 */
@EnableJpaRepositories
@EnableConfigurationProperties({JpaProperties.class, HibernateProperties.class, SpringDataWebProperties.class})
@ComponentScan(basePackages = DataAutoConfiguration.BASE_PACKAGE)
@AutoConfigurationPackage(basePackages = DataAutoConfiguration.BASE_PACKAGE)
@ConditionalOnClass(JpaRepository.class)
@EnableTransactionManagement
@Configuration(proxyBeanMethods = false)
@Import(IcepointJpaRepositoryRegistrar.class)
public class DataAutoConfiguration extends IcepointFrameworkBaseConfiguration {

    public static final String BASE_PACKAGE = "com.icepoint.framework.data";

    public static final Class<?> REPOSITORY_BASE_CLASS = StdRepositoryImpl.class;

    public static final List<TypeFilter> TYPE_FILTERS;

    static {
        List<TypeFilter> typeFilters = new ArrayList<>();
        typeFilters.add(new AnnotationTypeFilter(Repository.class));

        TYPE_FILTERS = Collections.unmodifiableList(typeFilters);
    }

    @Override
    public void configure(PackageScanRegistry registry) {
        registry.addBasePackages(BASE_PACKAGE);
        registry.addBasePackages(AutoConfigurationPackages.get(getBeanFactory()));
    }

    /**
     * 配置{@link org.springframework.data.domain.Pageable}参数解析处理器
     *
     * @return PageableHandlerMethodArgumentResolverCustomizer
     */
    @Bean
    public PageableCustomizer pageableCustomizer(SpringDataWebProperties properties) {
        return new PageableCustomizer(properties);
    }

    // jpa

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaProperties properties,
                                                                       HibernateProperties hibernateProperties, DataSource dataSource, PackageScanRegistry registry,
                                                                       Environment environment) {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        if (EnvironmentUtils.isDev(environment)) {
            adapter.setGenerateDdl(true);
        } else {
            adapter.setGenerateDdl(properties.isGenerateDdl());
        }

        adapter.setShowSql(properties.isShowSql());
        if (properties.getDatabase() != null) {
            adapter.setDatabase(properties.getDatabase());
        }
        if (properties.getDatabasePlatform() != null) {
            adapter.setDatabasePlatform(properties.getDatabasePlatform());
        }

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(registry.getBasePackages().toArray(new String[]{}));
        factory.setJpaPropertyMap(getVendorProperties(properties, hibernateProperties));
        factory.setMappingResources(getMappingResources(properties));

        return factory;
    }

    protected Map<String, Object> getVendorProperties(JpaProperties properties,
                                                      HibernateProperties hibernateProperties) {
        Supplier<String> defaultDdlMode = () -> "none";
        return new LinkedHashMap<>(hibernateProperties
                .determineHibernateProperties(properties.getProperties(), new HibernateSettings()
                        .ddlAuto(defaultDdlMode)));
    }

    private String[] getMappingResources(JpaProperties properties) {
        List<String> mappingResources = properties.getMappingResources();
        return (!ObjectUtils.isEmpty(mappingResources) ? StringUtils.toStringArray(mappingResources) : new String[0]);
    }

    @Configuration(proxyBeanMethods = false)
    @EnableJpaAuditing
    @ConditionalOnClass(User.class)
    public static class DataSecurityAutoConfiguration {

        @Bean
        public AuditorAware<Serializable> idAuditorAware() {
            return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getPrincipal)
                    .filter(BaseEntity.class::isInstance)
                    .map(BaseEntity.class::cast)
                    .map(BaseEntity::getId);
        }

    }

    // mybatis
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(PackageScanRegistry registry) {

        Set<String> basePackages = registry.getBasePackages();
        List<String> autoConfigurationPackages = AutoConfigurationPackages.get(getBeanFactory());

        Set<String> removeAutoPackages = new TreeSet<>(basePackages);
        autoConfigurationPackages.forEach(removeAutoPackages::remove);

        String basePackage = String.join(",", removeAutoPackages);

        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setAnnotationClass(Mapper.class);
        configurer.setBasePackage(basePackage);

        return configurer;
    }

    /**
     * 通过反射替换掉原本的MapperRegistry，最终目的是替换掉MapperMethod的行为，
     * <p>
     * 吐槽下Mybatis的设计，Mybatis Plus也几乎没有做优化，沿用了Mybatis初始化结构，不好扩展，很多地方都写死了实现类，
     * 尤其是{@link org.apache.ibatis.binding.MapperMethod}这个类，这个类作为定义SqlSession执行行为的类，没有任何可扩展点，完全写死
     *
     * @return MybatisPlusPropertiesCustomizer
     */
    @Bean
    public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
        return properties -> {
            MybatisConfiguration configuration = properties.getConfiguration();
            if (configuration == null) {
                configuration = new MybatisConfiguration();
                properties.setConfiguration(configuration);
            }

            String registryProperty = "mybatisMapperRegistry";
            MybatisMapperRegistry registry = FieldUtils.getRequiredField(configuration, registryProperty);

            if (!(registry instanceof MapperRegistry)) {
                MapperRegistry newRegistry = new MapperRegistry(configuration);
                FieldUtils.setField(configuration, registryProperty, newRegistry);
            }

        };
    }

}
