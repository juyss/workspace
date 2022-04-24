package com.icepoint.framework.generator.entity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jiawei Zhao
 */
public class ModuleRowMapper implements RowMapper<Module> {

    public static final ModuleRowMapper INSTANCE = new ModuleRowMapper();

    @Override
    public Module mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {

        if (rs == null)
            return null;

        Module module = new Module();
        int index = 0;

        module.setId(rs.getObject(++index, Long.class));
        module.setParentId(rs.getObject(++index, Long.class));
        module.setName(rs.getString(++index));
        module.setPath(rs.getString(++index));
        module.setOrder(rs.getObject(++index, Integer.class));
        module.setNote(rs.getString(++index));
        module.setAppId(rs.getObject(++index, Long.class));
        module.setOwnerId(rs.getObject(++index, Long.class));
        module.setCreateTime(rs.getObject(++index, Long.class));
        module.setCreateUser(rs.getObject(++index, Long.class));
        module.setUpdateTime(rs.getObject(++index, Long.class));
        module.setUpdateUser(rs.getObject(++index, Long.class));
        module.setDeleted(rs.getObject(++index, Boolean.class));

        return module;
    }
}
