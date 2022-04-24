package com.icepoint.framework.web.system.support;

import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.util.EntityStreams;
import com.icepoint.framework.data.util.PersistenceUtils;
import com.icepoint.framework.web.system.dao.ModuleRepository;
import com.icepoint.framework.web.system.dao.ResourceRepository;
import com.icepoint.framework.web.system.dao.TableMetadataRepository;
import com.icepoint.framework.web.system.dao.TableServiceRepository;
import com.icepoint.framework.web.system.entity.*;
import com.icepoint.framework.web.system.util.RestRepositoryContextHolder;
import org.jetbrains.annotations.Contract;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.core.Path;
import org.springframework.data.rest.core.mapping.ResourceMappings;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
//@Component
public class JpaEntityUrlResolver extends AbstractEntityUrlResolver {

    private final ResourceRepository resourceRepository;

    private final TableMetadataRepository tableMetadataRepository;

    private final TableServiceRepository tableServiceRepository;

    private final ModuleRepository moduleRepository;

    private final Repositories repositories;

    private Module module;

    private boolean moduleResolved = false;

    private TableService tableService;

    private String url;

    private boolean urlResolved = false;

    public JpaEntityUrlResolver(
            ResourceRepository resourceRepository,
            TableMetadataRepository tableMetadataRepository,
            TableServiceRepository tableServiceRepository,
            ModuleRepository moduleRepository,
            Repositories repositories) {

        this.resourceRepository = resourceRepository;
        this.tableMetadataRepository = tableMetadataRepository;
        this.tableServiceRepository = tableServiceRepository;
        this.moduleRepository = moduleRepository;
        this.repositories = repositories;
    }

    @Nullable
    @Override
    public String getUrlByResourceId(Long id) {

        nonnullId(id);

        return resourceRepository.findById(id, false)
                .flatMap(this::getUrl)
                .orElse(null);
    }

    @Nullable
    @Override
    public String getUrlByTableMetadataId(Long id) {

        nonnullId(id);

        return getTableMetadataById(id)
                .flatMap(this::getUrl)
                .orElse(null);
    }

    @Nullable
    @Override
    public String getUrlByTableServiceId(Long id) {

        nonnullId(id);

        return tableServiceRepository.findById(id, false)
                .flatMap(this::getUrl)
                .orElse(null);
    }

    @Nullable
    @Override
    public String getUrl(Class<?> entityType) {

        String tableName = PersistenceUtils.getTableName(entityType);
        QTableMetadata qTableMetadata = QTableMetadata.tableMetadata;

        return tableMetadataRepository.findOne(qTableMetadata.name.eq(tableName), false)
                .flatMap(this::getUrl)
                .orElse(null);

    }

    @Nullable
    @Override
    public Resource getResource(String url) {
        return Optional.ofNullable(getTableMetadata(url))
                .map(TableMetadata::getResource)
                .orElse(null);

    }

    @Nullable
    @Override
    public TableMetadata getTableMetadata(String url) {
        return Optional.ofNullable(getTableService(url))
                .map(TableService::getTableMetadata)
                .orElse(null);
    }

    @Nullable
    @Override
    public TableService getTableService(String url) {

        Class<Object> entityType = getEntityType(url);
        if (entityType == null) {
            return null;
        }

        EntityUrlBuilder builder = EntityUrlBuilder.fromUrl(url);
        Assert.isTrue(builder.size() >= 2, "查找的url至少要有2个片段");

        String serviceSegment = builder.get(1);
        QTableService qTableService = QTableService.tableService;
        List<TableService> tableServices = tableServiceRepository.findAll(
                qTableService.name.eq(serviceSegment),
                false);

        if (tableServices.isEmpty()) {
            return null;
        } else if (tableServices.size() == 1) {
            return tableServices.get(0);
        } else {

            String moduleSegment = builder.getFirst();
            QModule qModule = QModule.module;

            return moduleRepository.findOne(qModule.name.eq(moduleSegment), false)
                    .map(Module::getTableMetadatas)
                    .map(EntityStreams::collectIdList)
                    .map(tableIds -> Streams.streamable(tableServices)
                            .filter(service -> tableIds.contains(service.getTableId()))
                            .toList())
                    .map(filteredTableServices -> {

                        if (filteredTableServices.isEmpty()) {
                            return null;
                        }

                        Assert.isTrue(filteredTableServices.size() == 1, () -> {

                            List<String> ids = EntityStreams.idStreamable(filteredTableServices)
                                    .map(String::valueOf)
                                    .toList();

                            return String.format(
                                    "该路径匹配的Table Service有多个, path: %s, matches ids: [%s]",
                                    url,
                                    String.join(", ", ids));
                        });

                        return filteredTableServices.get(0);
                    })
                    .orElse(null);
        }
    }

    @Nullable
    @Override
    public Module getModule(String url) {

        Class<Object> entityType = getEntityType(url);
        if (entityType == null) {
            return null;
        }

        EntityUrlBuilder builder = EntityUrlBuilder.fromUrl(url);
        Assert.isTrue(builder.size() >= 2, "查找的url至少要有2个片段");

        String moduleSegment = builder.getFirst();
        QModule qModule = QModule.module;

        return moduleRepository.findOne(qModule.name.eq(moduleSegment), false)
                .orElse(null);
    }

    @Nullable
    @Override
    public <T> Class<T> getEntityType(String url) {

        ResourceMappings mappings = RestRepositoryContextHolder.getMappings();

        List<Class<?>> matches = new ArrayList<>();
        for (Class<?> domainType : repositories) {

            ResourceMetadata metadata = mappings.getMetadataFor(domainType);
            Path metadataPath = metadata.getPath();

            if (url.startsWith(metadataPath.toString())) {
                matches.add(domainType);
            }
        }

        if (matches.isEmpty()) {

            return null;

        } else if (matches.size() > 1) {

            String ambiguous = matches.stream()
                    .map(Class::getName)
                    .collect(Collectors.joining(","));

            throw new IllegalStateException("该路径匹配的实体有多个, path: %s, matches: [" + ambiguous + "]");

        } else {

            return CastUtils.cast(matches.get(0));
        }
    }

    @Contract("null -> fail")
    private void nonnullId(@Nullable Long id) {
        Assert.notNull(id, MessageTemplates.notNull("id"));
    }

    @Nullable
    private Module getModuleByTableMetadata(TableMetadata metadata) {

        if (module != null) {
            return module;
        }

        Module m = metadata.getModule();
        if (m != null) {
            return m;
        }

        Long moduleId = metadata.getModuleId();
        Assert.notNull(moduleId, "moduleId为空");

        return moduleRepository.findById(moduleId, false)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "该表对应的模块数据不存在, 表名称: \"%s\", 模块id: %s",
                        metadata.getName(), moduleId)));
    }

    private String resolveUrl() {

        if (urlResolved) {
            return url;
        }

        Assert.notNull(tableService, "TableService在这一步一定不为空");

        String moduleSegment = null;
        String serviceSegment = getServiceSegment(tableService);
        Assert.hasText(serviceSegment, "表服务名称为空");

        // 尝试获取module
        if (moduleResolved && module != null) {
            moduleSegment = getModuleSegment(module);
        }

        // module没有被查询过只可能是从TableService开始查找的
        if (moduleSegment == null) {
            Long tableId = tableService.getTableId();
            Assert.notNull(tableId, MessageTemplates.notNull("tableId"));
            TableMetadata metadata = getTableMetadataById(tableId)
                    .orElseThrow(() -> new IllegalStateException(
                            "该表服务对应的表元数据不存在: " + tableService.getName() + ": " + tableId));

            module = getModuleByTableMetadata(metadata);
            Assert.notNull(module, String.format("该表对应的模块数据不存在, 表名称: \"%s\", 模块id: %s",
                    metadata.getName(), metadata.getModuleId()));

            moduleSegment = getModuleSegment(module);
            Assert.hasText(moduleSegment, "模块名称为空");
        }

        url = getBuilder(moduleSegment, serviceSegment).getPath();
        urlResolved = true;
        Assert.hasText(url, " url为空");
        return url;
    }

    private Optional<TableMetadata> getTableMetadataById(Long id) {
        nonnullId(id);
        return tableMetadataRepository.findById(id, false);
    }

    private Optional<String> getUrl(Resource resource) {

        Assert.notNull(resource, MessageTemplates.notNull("Resource"));

        return Optional.ofNullable(resource.getTableMetadata())
                .flatMap(this::getUrl);
    }

    private Optional<String> getUrl(TableMetadata tableMetadata) {

        Assert.notNull(tableMetadata, MessageTemplates.notNull("TableMetadata"));

        this.module = getModuleByTableMetadata(tableMetadata);
        this.moduleResolved = true;

        return Optional.ofNullable(tableMetadata.getTableService())
                .flatMap(this::getUrl);
    }

    private Optional<String> getUrl(TableService tableService) {

        Assert.notNull(tableService, MessageTemplates.notNull("TableService"));

        this.tableService = tableService;
        return Optional.of(resolveUrl());
    }

}
