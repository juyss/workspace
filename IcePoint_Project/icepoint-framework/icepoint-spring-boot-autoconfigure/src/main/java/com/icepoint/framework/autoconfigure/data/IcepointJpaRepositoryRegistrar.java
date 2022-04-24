package com.icepoint.framework.autoconfigure.data;

import com.icepoint.framework.autoconfigure.PackageScanRegistry;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.data.AbstractRepositoryConfigurationSourceSupport;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.config.AnnotationRepositoryConfigurationSource;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.data.repository.config.RepositoryConfigurationDelegate;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;
import org.springframework.data.util.Optionals;
import org.springframework.data.util.Streamable;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
class IcepointJpaRepositoryRegistrar extends AbstractRepositoryConfigurationSourceSupport
        implements ResourceLoaderAware, EnvironmentAware, BeanFactoryAware {

    private BootstrapMode bootstrapMode = null;

    private ResourceLoader resourceLoader;

    private Environment environment;

    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
            BeanNameGenerator importBeanNameGenerator) {

        RepositoryConfigurationDelegate delegate = new RepositoryConfigurationDelegate(
                getSource(registry, importBeanNameGenerator), this.resourceLoader, this.environment);

        delegate.registerRepositoriesIn(registry, getRepositoryConfigurationExtension());
    }

    private AnnotationRepositoryConfigurationSource getSource(
            BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {

        AnnotationMetadata metadata = AnnotationMetadata.introspect(getConfiguration());

        return new AnnotationRepositoryConfigurationSource(metadata, getAnnotation(), this.resourceLoader,
                this.environment, registry, importBeanNameGenerator) {

            @Override
            public Streamable<String> getBasePackages() {
                return super.getBasePackages()
                        .and(IcepointJpaRepositoryRegistrar.this.getBasePackages());
            }

            @Override
            public Optional<String> getRepositoryBaseClassName() {
                return Optionals.firstNonEmpty(
                        super::getRepositoryBaseClassName,
                        () -> Optional.of(DataAutoConfiguration.REPOSITORY_BASE_CLASS.getName())
                );
            }

            @Override
            protected Iterable<TypeFilter> getIncludeFilters() {
                List<TypeFilter> typeFilters = new ArrayList<>(DataAutoConfiguration.TYPE_FILTERS);
                super.getIncludeFilters().forEach(typeFilters::add);
                return typeFilters;
            }
        };
    }

    @Override
    public Streamable<String> getBasePackages() {
        PackageScanRegistry registry = beanFactory.getBean(PackageScanRegistry.class);
        return Streamable.of(registry.getBasePackages());
    }

    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return EnableJpaRepositories.class;
    }

    @Override
    protected Class<?> getConfiguration() {
        return EnableJpaRepositoriesConfiguration.class;
    }

    @Override
    protected RepositoryConfigurationExtension getRepositoryConfigurationExtension() {
        return new JpaRepositoryConfigExtension();
    }

    @Override
    protected BootstrapMode getBootstrapMode() {
        return (this.bootstrapMode == null) ? BootstrapMode.DEFAULT : this.bootstrapMode;
    }

    @Override
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
        this.environment = environment;
        configureBootstrapMode(environment);
    }

    private void configureBootstrapMode(Environment environment) {
        String property = environment.getProperty("spring.data.jpa.repositories.bootstrap-mode");
        if (StringUtils.hasText(property)) {
            this.bootstrapMode = BootstrapMode.valueOf(property.toUpperCase(Locale.ENGLISH));
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        super.setResourceLoader(resourceLoader);
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
        this.beanFactory = beanFactory;
    }

    @EnableJpaRepositories
    private static class EnableJpaRepositoriesConfiguration {

    }
}
