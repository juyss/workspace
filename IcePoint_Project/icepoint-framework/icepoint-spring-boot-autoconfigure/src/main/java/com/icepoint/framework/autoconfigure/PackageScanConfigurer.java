package com.icepoint.framework.autoconfigure;

/**
 * @author Jiawei Zhao
 */
public interface PackageScanConfigurer {

    default void configure(PackageScanRegistry registry) {
    }
}
