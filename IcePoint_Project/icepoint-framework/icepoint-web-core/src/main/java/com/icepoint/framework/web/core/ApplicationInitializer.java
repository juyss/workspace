package com.icepoint.framework.web.core;

/**
 * @author Jiawei Zhao
 */
public interface ApplicationInitializer {

    void initialize() throws DatabaseValidationFailedException;
}
