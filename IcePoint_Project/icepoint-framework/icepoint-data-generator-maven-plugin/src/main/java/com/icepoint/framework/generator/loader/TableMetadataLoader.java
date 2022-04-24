package com.icepoint.framework.generator.loader;

import com.icepoint.framework.generator.entity.*;
import org.apache.maven.plugin.MojoFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class TableMetadataLoader extends AbstractLoader<TableMetadata> {

    private final Long resourceId;

    public static final Map<Long, TableMetadata> CACHE = new HashMap<>();

    public TableMetadataLoader(JdbcTemplate jdbcTemplate, Long resourceId) {
        super(jdbcTemplate);
        this.resourceId = resourceId;
    }

    @Override
    public TableMetadata load() throws MojoFailureException {

        TableMetadata table = jdbcTemplate.queryForObject("SELECT id, resource_id, name, name_alias, `order`, " +
                "app_id, owner_id, create_time, create_user_id, " +
                "update_time, update_user_id, deleted " +
                "FROM sys_table_metadata " +
                "WHERE deleted = 0 AND resource_id = ?;", TableMetadataRowMapper.INSTANCE, resourceId);

        if (table == null)
            return null;

        TableMetadata cacheTable = CACHE.get(table.getId());
        table = cacheTable == null ? table : cacheTable;

        putCache(table);

        List<TableLink> links = loadTableLinks(table.getId());
        if (!CollectionUtils.isEmpty(links)) {
            table.setLinks(links);
        }

        List<FieldMetadata> fieldMetadata = new FieldMetadataLoader(jdbcTemplate, table.getId()).load();
        if (CollectionUtils.isEmpty(fieldMetadata)) {
            throw new MojoFailureException("Table: "+ table.getName() + " 找不到对应的字段元数据");
        }

        table.setFields(fieldMetadata);

        return table;
    }

    public static void putCache(TableMetadata table) {
        CACHE.merge(table.getId(), table, (a, b) -> {
            List<TableLink> links = a.getLinks() == null ? b.getLinks() : a.getLinks();
            List<FieldMetadata> fields = a.getFields() == null ? b.getFields() : a.getFields();

            a.setLinks(links);
            a.setFields(fields);

            return a;
        });
    }

    public static TableMetadata getTableMetadataById(Long id) {

        TableMetadata table = CACHE.get(id);
        if (table != null)
            return table;

        table = jdbcTemplate.queryForObject("SELECT id, resource_id, name, name_alias, `order`, " +
                "app_id, owner_id, create_time, create_user_id, " +
                "update_time, update_user_id, deleted " +
                "FROM sys_table_metadata " +
                "WHERE deleted = 0 AND id = ?;", TableMetadataRowMapper.INSTANCE, id);

        putCache(table);
        return table;
    }

    private List<TableLink> loadTableLinks(Long tableId) throws MojoFailureException {

        List<TableLink> links = jdbcTemplate.query("SELECT id, table_id, fk_field_id, link_table_id, " +
                "association_type, name, name_alias " +
                "FROM sys_table_link " +
                "WHERE table_id = ?;", TableLinkRowMapper.INSTANCE, tableId);

        for (TableLink link : links) {
            Long linkTableId = link.getLinkTableId();
            if (linkTableId == null) {
                throw new MojoFailureException("Table Link的linkTableId为空, link id: " + link.getId());
            }

            TableMetadata linkTable = getTableMetadataById(linkTableId);
            if (linkTable == null) {
                throw new MojoFailureException("找不到该id的Table, id: " + linkTableId);
            }
            link.setLinkTable(linkTable);

            Long fkFieldId = link.getFkFieldId();
            if (fkFieldId == null) {
                throw new MojoFailureException("Table Link的fkFieldId为空, link id: " + link.getId());
            }

            FieldMetadata fkField = FieldMetadataLoader.getCache(fkFieldId);
            if (fkField == null) {
                throw new MojoFailureException("找不到该id的Field, id: " + fkField);
            }
            link.setFkField(fkField);
        }

        return links;
    }
}
