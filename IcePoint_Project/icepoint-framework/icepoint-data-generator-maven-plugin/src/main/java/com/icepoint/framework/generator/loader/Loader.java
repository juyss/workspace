package com.icepoint.framework.generator.loader;

import org.apache.maven.plugin.MojoFailureException;

/**
 * @author Jiawei Zhao
 */
public interface Loader<T> {

    T load() throws MojoFailureException;
}
