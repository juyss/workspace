package com.icepoint.framework.generator;

import com.icepoint.framework.generator.entity.Resource;
import com.icepoint.framework.generator.loader.ResourceLoader;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
@Mojo(name = "process", requiresDependencyResolution = ResolutionScope.COMPILE)
public class ProcessMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    private String basePackage;

    @Parameter(property = "dataSource")
    private DataSourceMetadata dataSource;

    private ClassLoader classLoader;

    @Parameter(property = "outputDirectory")
    private String outputDirectory = "src/main/java";


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        getLog().info("");

        classLoader = MavenUtils.getClassLoader(this, project);
        basePackage = getBasePackage();

        getLog().info("basePackage解析完成: " + basePackage);

        getLog().info("");
        getLog().info("正在加载数据源: " + dataSource.getUrl());

        DataSource ds = loadDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        getLog().info("数据源加载成功");

        ResourceLoader resourceLoader = new ResourceLoader(jdbcTemplate);

        getLog().info("");
        getLog().info("正在加载Resources...");

        List<Resource> resources = resourceLoader.load();
        List<EntityGenerator> generators = getEntityGenerators(resources);

        getLog().info("Resources加载完毕, 正在生成实体类...");
        getLog().info("");

        String generateSourceRoot = MavenUtils.getProjectRoot(project) + "/" + outputDirectory;
        Map<Long, EntityGenerator> generatedEntitiesByTableId = new HashMap<>();

        for (EntityGenerator generator : generators) {
            generator.init();
            generatedEntitiesByTableId.put(generator.getResource().getTable().getId(), generator);
        }

        for (EntityGenerator generator : generators) {
            generator.handleAssociations(generatedEntitiesByTableId);
        }

        for (EntityGenerator generator : generators) {
            generator.generate(generateSourceRoot);
        }
        getLog().info("");
    }

    private List<EntityGenerator> getEntityGenerators(List<Resource> resources) {
        return resources.stream()
                .filter(Objects::nonNull)
                .map(resource -> new EntityGenerator(basePackage, resource, classLoader, getLog()))
                .collect(Collectors.toList());
    }


    private DataSource loadDataSource() throws MojoExecutionException {
        try {
            return new SimpleDriverDataSource(
                    (Driver) BeanUtils.instantiateClass(Class.forName(dataSource.getDriver(), true, classLoader)),
                    dataSource.getUrl(),
                    dataSource.getUsername(),
                    dataSource.getPassword()
            );
        } catch (ClassNotFoundException e) {
            throw new MojoExecutionException("数据源Driver加载异常", e);
        }
    }

    private String getBasePackage() throws MojoFailureException {

        FileSystemResourceLoader loader = new FileSystemResourceLoader();
        loader.setClassLoader(classLoader);

        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(SpringBootApplication.class));
        provider.setResourceLoader(loader);

        Set<BeanDefinition> components = provider.findCandidateComponents("");
        ScannedGenericBeanDefinition definition;

        if (CollectionUtils.isEmpty(components)) {
            throw new MojoFailureException("找不到SpringBootApplication注解的类");
        } else if (components.size() > 1) {
            throw new MojoFailureException("不能同时存在多个SpringBootApplication注解的类");
        } else {
            definition = (ScannedGenericBeanDefinition) components.iterator().next();
        }

        getLog().info("成功读取启动类: " + definition.getBeanClassName());
        String[] basePackages = definition
                .getMetadata()
                .getAnnotations()
                .get(ComponentScan.class)
                .getStringArray("basePackages");

        if (basePackages.length == 0) {

            Class<?> beanClass;
            try {
                beanClass = definition.resolveBeanClass(classLoader);
                if (beanClass == null) {
                    throw new ClassNotFoundException();
                }

            } catch (ClassNotFoundException e) {
                getLog().error("启动类Class对象加载异常: " + definition.getBeanClassName());
                throw new MojoFailureException(e.getMessage(), e);
            }

            return beanClass.getPackage().getName();
        } else {
            return basePackages[0];
        }

    }
}
