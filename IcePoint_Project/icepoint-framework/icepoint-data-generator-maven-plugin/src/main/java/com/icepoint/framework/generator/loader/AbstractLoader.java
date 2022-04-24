package com.icepoint.framework.generator.loader;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractLoader<T> implements Loader<T> {

    protected static JdbcTemplate jdbcTemplate;

    protected AbstractLoader(JdbcTemplate jdbcTemplate) {
        AbstractLoader.jdbcTemplate = jdbcTemplate;
    }
}
