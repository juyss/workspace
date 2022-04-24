package com.icepoint.framework.autoconfigure.web.security.customizer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author Jiawei Zhao
 */
@FunctionalInterface
public interface HttpSecurityConfigurer {

    void configure(HttpSecurity http) throws Exception;

}
