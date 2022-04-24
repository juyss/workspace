package com.icepoint.framework.generator.loader;

import com.icepoint.framework.generator.entity.*;
import org.apache.maven.plugin.MojoFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jiawei Zhao
 */
public class ResourceLoader extends AbstractLoader<List<Resource>> {

    public ResourceLoader(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Resource> load() throws MojoFailureException {

        List<Resource> resources = jdbcTemplate.query("SELECT id, module_id, parent_id, name, name_alias, " +
                "code, type, expose_default, `order`, description, " +
                "app_id, owner_id, create_time, create_user_id, update_time, update_user_id, deleted " +
                "FROM sys_resource " +
                "WHERE deleted = 0;", ResourceRowMapper.INSTANCE);

        Set<Long> resolvedTableIds = new HashSet<>();

        for (Resource resource : resources) {
            
            loadModule(resource);

            loadTable(resolvedTableIds, resource);
        }

        return resources;
    }

    private void loadTable(Set<Long> resolvedTableIds, Resource resource) throws MojoFailureException {
        TableMetadata tableMetadata = new TableMetadataLoader(jdbcTemplate, resource.getId()).load();
        Long tableId = tableMetadata.getId();
        if (resolvedTableIds.contains(tableId)) {
            throw new MojoFailureException("有两个Resource指向了同一个Table, resource: " + resource.getName()
                    + ", table id: " + tableMetadata.getId());
        }

        resource.setTable(tableMetadata);

        resolvedTableIds.add(tableId);
    }

    private void loadModule(Resource resource) throws MojoFailureException {
        Long moduleId = resource.getModuleId();
        if (moduleId == null) {
            throw new MojoFailureException("无法找到资源所属模块, module id为空, resource: " + resource.getName());
        }

        Module module = getModuleAndParents(resource.getModuleId());
        if (module == null) {
            throw new MojoFailureException("该资源没有所属模块, 将被忽略, resource: " + resource.getName());
        }

        resource.setModule(module);
    }

    private Module getModuleAndParents(Long id) {

        Module module = getModule(id);
        if (module == null)
            return null;

        Long parentId = module.getParentId();
        if (parentId != null) {
            Module parent = getModuleAndParents(parentId);
            if (parent != null) {
                module.setParent(parent);
            }
        }

        return module;
    }

    @Nullable
    private Module getModule(Long id) {
        if (id == null)
            return null;

        List<Module> list = jdbcTemplate.query("SELECT id, parent_id, name, path, `order`, " +
                "note, app_id, owner_id, create_user_id, " +
                "create_time, update_user_id, update_time, deleted " +
                "FROM sys_module " +
                "WHERE deleted = 0 AND id = ?;", ModuleRowMapper.INSTANCE, id);

        return !list.isEmpty() ? list.get(0) : null;

    }
}
