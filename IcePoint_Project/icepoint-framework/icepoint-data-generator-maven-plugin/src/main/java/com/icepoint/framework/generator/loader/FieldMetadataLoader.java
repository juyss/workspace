package com.icepoint.framework.generator.loader;

import com.icepoint.framework.generator.entity.FieldMetadata;
import com.icepoint.framework.generator.entity.FieldMetadataRowMapper;
import org.apache.maven.plugin.MojoFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class FieldMetadataLoader extends AbstractLoader<List<FieldMetadata>> {

    private final Long tableId;

    private static final Map<Long, FieldMetadata> CACHE = new HashMap<>();

    public FieldMetadataLoader(JdbcTemplate jdbcTemplate, Long tableId) {
        super(jdbcTemplate);
        this.tableId = tableId;
    }

    @Override
    public List<FieldMetadata> load() throws MojoFailureException {

        List<FieldMetadata> fields = jdbcTemplate.query("SELECT id, table_id, name, name_alias, display_name, " +
                "java_type, native_type, default_value, primary_key, " +
                "optional, list_field, queryable, dict_field, " +
                "dict_category, max_length, max, min, `order`, " +
                "sortable, description, app_id, owner_id, create_time, " +
                "create_user_id, update_time, update_user_id, deleted " +
                "FROM sys_field_metadata " +
                "WHERE table_id = ? AND deleted = 0;", FieldMetadataRowMapper.INSTANCE, tableId);

        if (CollectionUtils.isEmpty(fields)) {
            throw new MojoFailureException("找不到对应的Field Metadata, Table id: " + tableId);
        }

        fields.forEach(FieldMetadataLoader::putCache);
        return fields;
    }

    public static void putCache(FieldMetadata field) {
        CACHE.merge(field.getId(), field, (a, b) -> {
            throw new IllegalStateException("重复的field id");
        });
    }

    public static FieldMetadata getCache(Long id) {
        FieldMetadata field = CACHE.get(id);
        if (field == null) {
            field = getFieldMetadataById(id);
            if (field != null) {
                putCache(field);
            }
        }

        return field;
    }

    private static FieldMetadata getFieldMetadataById(Long id) {
        return jdbcTemplate.queryForObject("SELECT id, table_id, name, name_alias, display_name, " +
                "java_type, native_type, default_value, primary_key, " +
                "optional, list_field, queryable, dict_field, " +
                "dict_category, max_length, max, min, `order`, " +
                "sortable, description, app_id, owner_id, create_time, " +
                "create_user_id, update_time, update_user_id, deleted " +
                "FROM sys_field_metadata " +
                "WHERE id = ? AND deleted = 0;", FieldMetadataRowMapper.INSTANCE, id);
    }
}
