package com.icepoint.framework.web.system.support;

import com.icepoint.framework.web.system.dao.ModuleRepository;
import com.icepoint.framework.web.system.dao.ResourceRepository;
import com.icepoint.framework.web.system.dao.TableMetadataRepository;
import com.icepoint.framework.web.system.dao.TableServiceRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.util.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@Component
public class JpaEntityUrlResolverFactory extends AbstractEntityUrlResolverFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final Lazy<ResourceRepository> resourceRepository;
    private final Lazy<TableMetadataRepository> tableMetadataRepository;
    private final Lazy<TableServiceRepository> tableServiceRepository;
    private final Lazy<ModuleRepository> moduleRepository;
    private final Lazy<Repositories> repositories;

    public JpaEntityUrlResolverFactory() {
        this.resourceRepository = Lazy.of(() -> getBean(ResourceRepository.class));
        this.tableMetadataRepository = Lazy.of(() -> getBean(TableMetadataRepository.class));
        this.tableServiceRepository = Lazy.of(() -> getBean(TableServiceRepository.class));
        this.moduleRepository = Lazy.of(() -> getBean(ModuleRepository.class));
        this.repositories = Lazy.of(() -> getBean(Repositories.class));
    }

    @Override
    public EntityUrlResolver getObject() {
        return new JpaEntityUrlResolver(
                resourceRepository.get(),
                tableMetadataRepository.get(),
                tableServiceRepository.get(),
                moduleRepository.get(),
                repositories.get()
        );
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
