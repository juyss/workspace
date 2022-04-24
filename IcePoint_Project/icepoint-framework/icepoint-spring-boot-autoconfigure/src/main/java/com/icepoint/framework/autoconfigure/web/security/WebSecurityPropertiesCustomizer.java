package com.icepoint.framework.autoconfigure.web.security;

/**
 * @author Jiawei Zhao
 */
@FunctionalInterface
public interface WebSecurityPropertiesCustomizer {

    void customize(WebSecurityProperties properties);
}
