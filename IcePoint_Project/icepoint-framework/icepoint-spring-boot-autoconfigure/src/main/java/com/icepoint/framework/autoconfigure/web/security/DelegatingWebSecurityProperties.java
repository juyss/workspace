package com.icepoint.framework.autoconfigure.web.security;

import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Set;

/**
 * @author Jiawei Zhao
 */
@EqualsAndHashCode(callSuper = true)
public class DelegatingWebSecurityProperties extends WebSecurityProperties {

    private final WebSecurityProperties delegate;

    public DelegatingWebSecurityProperties(WebSecurityProperties delegate) {
        this.delegate = delegate;
    }

    @Override
    public Set<String> getIgnorePaths() {
        return this.delegate.getIgnorePaths();
    }

    @Override
    public void addIgnorePath(String path) {
        this.delegate.addIgnorePath(path);
    }

    @Override
    public void addIgnorePaths(Collection<String> paths) {
        this.delegate.addIgnorePaths(paths);
    }

    @Override
    public void setIgnorePaths(Set<String> ignorePaths) {
        this.delegate.setIgnorePaths(ignorePaths);
    }
}
