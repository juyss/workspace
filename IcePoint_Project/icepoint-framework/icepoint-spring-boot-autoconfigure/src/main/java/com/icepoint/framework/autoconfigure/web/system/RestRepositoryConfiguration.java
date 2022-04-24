package com.icepoint.framework.autoconfigure.web.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icepoint.framework.web.system.rest.MetadataRepositoryDetectionStrategy;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.util.TypeInformation;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class RestRepositoryConfiguration extends DelegatingWebMvcConfiguration implements RepositoryRestConfigurer {

    private final PersistentEntities entities;

    public RestRepositoryConfiguration(PersistentEntities entities) {
        this.entities = entities;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        exposeEntitiesId(config);

        config.setRepositoryDetectionStrategy(new MetadataRepositoryDetectionStrategy());

        configureExposure(config.getExposureConfiguration());

        configureRetuning(config);

        config.useHalAsDefaultJsonMediaType(false);
    }

    private void configureRetuning(RepositoryRestConfiguration config) {
        // 根据请求头Accept是否有值决定是否返回新增或修改后的数据
        // 有值返回数据, 没值不返回数据
        config.setReturnBodyForPutAndPost(null);
    }

    private void configureExposure(ExposureConfiguration configuration) {
        configuration.disablePutForCreation();
    }

    /**
     * Spring Data Rest默认不反序列化id字段，需要设置暴露id字段
     *
     * @param config RepositoryRestConfiguration
     */
    private void exposeEntitiesId(RepositoryRestConfiguration config) {

        List<? extends Class<?>> entityClasses = entities.getManagedTypes()
                .map(TypeInformation::getType)
                .toList();

        config.exposeIdsFor(entityClasses.toArray(new Class[0]));
    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        RepositoryRestConfigurer.super.configureJacksonObjectMapper(objectMapper);
    }

}
