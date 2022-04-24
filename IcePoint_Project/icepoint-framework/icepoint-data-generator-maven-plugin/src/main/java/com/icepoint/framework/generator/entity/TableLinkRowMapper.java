package com.icepoint.framework.generator.entity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author Jiawei Zhao
 */
public class TableLinkRowMapper implements RowMapper<TableLink> {

    public static final TableLinkRowMapper INSTANCE = new TableLinkRowMapper();

    @Override
    public TableLink mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {

        if (rs == null)
            return null;

        TableLink link = new TableLink();
        int index = 0;

        link.setId(rs.getObject(++index, Long.class));
        link.setTableId(rs.getObject(++index, Long.class));
        link.setFkFieldId(rs.getObject(++index, Long.class));
        link.setLinkTableId(rs.getObject(++index, Long.class));
        link.setAssociationType(convertAssociationType(rs.getString(++index)));
        link.setName(rs.getString(++index));
        link.setNameAlias(rs.getString(++index));

        return link;
    }

    private AssociationType convertAssociationType(String code) {
        return Arrays.stream(AssociationType.values())
                .filter(associationType -> associationType.getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
