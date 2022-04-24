package com.icepoint.framework.generator.entity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jiawei Zhao
 */
public class TableMetadataRowMapper implements RowMapper<TableMetadata> {

    public static final TableMetadataRowMapper INSTANCE = new TableMetadataRowMapper();

    @Override
    public TableMetadata mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {

        if (rs == null)
            return null;

        TableMetadata table = new TableMetadata();
        int index = 0;

        table.setId(rs.getObject(++index, Long.class));
        table.setResourceId(rs.getObject(++index, Long.class));
        table.setName(rs.getString(++index));
        table.setNameAlias(rs.getString(++index));
        table.setOrder(rs.getInt(++index));
        table.setAppId(rs.getObject(++index, Long.class));
        table.setOwnerId(rs.getObject(++index, Long.class));
        table.setCreateTime(rs.getObject(++index, Long.class));
        table.setCreateUser(rs.getObject(++index, Long.class));
        table.setUpdateTime(rs.getObject(++index, Long.class));
        table.setUpdateUser(rs.getObject(++index, Long.class));
        table.setDeleted(rs.getObject(++index, Boolean.class));

        return table;
    }
}
