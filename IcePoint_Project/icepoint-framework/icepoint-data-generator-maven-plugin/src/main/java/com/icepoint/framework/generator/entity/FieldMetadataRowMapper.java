package com.icepoint.framework.generator.entity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jiawei Zhao
 */
public class FieldMetadataRowMapper implements RowMapper<FieldMetadata> {

    public static final FieldMetadataRowMapper INSTANCE = new FieldMetadataRowMapper();

    @Override
    public FieldMetadata mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {

        if (rs == null) {
            return null;
        }

        FieldMetadata field = new FieldMetadata();
        int index = 0;

        field.setId(rs.getObject(++index, Long.class));
        field.setTableId(rs.getObject(++index, Long.class));
        field.setName(rs.getString(++index));
        field.setNameAlias(rs.getString(++index));
        field.setDisplayName(rs.getString(++index));
        field.setJavaType(rs.getString(++index));
        field.setNativeType(rs.getString(++index));
        field.setDefaultValue(rs.getString(++index));
        field.setPrimaryKey(rs.getObject(++index, Boolean.class));
        field.setOptional(rs.getObject(++index, Boolean.class));
        field.setListField(rs.getObject(++index, Boolean.class));
        field.setQueryable(rs.getObject(++index, Boolean.class));
        field.setDictField(rs.getObject(++index, Boolean.class));
        field.setDictCategory(rs.getString(++index));
        field.setMaxLength(rs.getObject(++index, Integer.class));
        field.setMax(rs.getObject(++index, Integer.class));
        field.setMin(rs.getObject(++index, Integer.class));
        field.setOrder(rs.getObject(++index, Integer.class));
        field.setSortable(rs.getObject(++index, Boolean.class));
        field.setDescription(rs.getString(++index));
        field.setAppId(rs.getObject(++index, Long.class));
        field.setOwnerId(rs.getObject(++index, Long.class));
        field.setCreateTime(rs.getObject(++index, Long.class));
        field.setCreateUser(rs.getObject(++index, Long.class));
        field.setUpdateTime(rs.getObject(++index, Long.class));
        field.setUpdateUser(rs.getObject(++index, Long.class));
        field.setDeleted(rs.getObject(++index, Boolean.class));

        return field;
    }
}
