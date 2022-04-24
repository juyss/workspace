package com.icepoint.framework.autoconfigure.web.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icepoint.framework.autoconfigure.IcepointFrameworkBaseConfiguration;
import com.icepoint.framework.autoconfigure.PackageScanRegistry;
import com.icepoint.framework.autoconfigure.data.DataAutoConfiguration;
import com.icepoint.framework.web.core.entity.Dict;
import com.icepoint.framework.web.core.message.Message;
import com.icepoint.framework.web.core.message.MessageRegistry;
import com.icepoint.framework.web.system.resource.ResourceModelViewResolver;
import com.icepoint.framework.web.system.support.ResourceModelAndViewSerializer;
import com.icepoint.framework.web.system.support.ResourceModelSerializer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Jiawei Zhao
 */
@EnableConfigurationProperties(WebSystemProperties.class)
@ConditionalOnClass(Dict.class)
@ComponentScan(WebSystemAutoConfiguration.BASE_PACKAGE)
@AutoConfigurationPackage(basePackages = WebSystemAutoConfiguration.BASE_PACKAGE)
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(DataAutoConfiguration.class)
@Import({ RestRepositoryConfiguration.class, RestMappingConfiguration.class, ResourceAutoConfiguration.class })
public class WebSystemAutoConfiguration extends IcepointFrameworkBaseConfiguration {

    public static final String BASE_PACKAGE = "com.icepoint.framework.web.system";

    @SuppressWarnings("ConstantConditions")
    @Bean
    public MessageRegistry messageRegistry(PackageScanRegistry packageScanRegistry) {

        MessageRegistry registry = new MessageRegistry();

        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(Message.class));

        Set<String> scanPackages = packageScanRegistry.getBasePackages();
        if (CollectionUtils.isEmpty(scanPackages)) {
            return registry;
        }

        scanPackages.stream()
                .map(provider::findCandidateComponents)
                .flatMap(Collection::stream)
                .map(AbstractBeanDefinition.class::cast)
                .map(definition -> {

                    try {
                        return definition.resolveBeanClass(WebSystemAutoConfiguration.class.getClassLoader());
                    } catch (ClassNotFoundException e) {
                        throw new UndeclaredThrowableException(e);
                    }

                })
                .filter(Enum.class::isAssignableFrom)
                .map(Class::getEnumConstants)
                .flatMap(Stream::of)
                .map(Message.class::cast)
                .forEach(registry::register);

        return registry;
    }

    @Override
    public void configure(PackageScanRegistry registry) {
        registry.addBasePackages(BASE_PACKAGE);
    }

    @ConditionalOnClass(ObjectMapper.class)
    @Configuration(proxyBeanMethods = false)
    static class SerializeAutoConfiguration {

        @Bean
        public Jackson2ObjectMapperBuilderCustomizer resourceModelObjectMapperBuilderCustomizer(
                ResourceModelViewResolver resolver) {
            return builder -> builder.serializers(
                    new ResourceModelSerializer(resolver),
                    new ResourceModelAndViewSerializer()
            );
        }

    }
}
