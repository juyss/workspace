package com.icepoint.framework.generator.entity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jiawei Zhao
 */
public class ResourceRowMapper implements RowMapper<Resource> {

    public static final ResourceRowMapper INSTANCE = new ResourceRowMapper();

    @Override
    public Resource mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {
        if (rs == null)
            return null;

        Resource resource = new Resource();
        int index = 0;

        resource.setId(rs.getObject(++index, Long.class));
        resource.setModuleId(rs.getObject(++index, Long.class));
        resource.setParentId(rs.getObject(++index, Long.class));
        resource.setName(rs.getString(++index));
        resource.setNameAlias(rs.getString(++index));
        resource.setCode(rs.getString(++index));
        resource.setType(rs.getString(++index));
        resource.setExposeDefault(rs.getObject(++index, Boolean.class));
        resource.setOrder(rs.getObject(++index, Integer.class));
        resource.setDescription(rs.getString(++index));
        resource.setAppId(rs.getObject(++index, Long.class));
        resource.setOwnerId(rs.getObject(++index, Long.class));
        resource.setCreateTime(rs.getObject(++index, Long.class));
        resource.setCreateUser(rs.getObject(++index, Long.class));
        resource.setUpdateTime(rs.getObject(++index, Long.class));
        resource.setUpdateUser(rs.getObject(++index, Long.class));
        resource.setDeleted(rs.getObject(++index, Boolean.class));

        return resource;
    }
}
