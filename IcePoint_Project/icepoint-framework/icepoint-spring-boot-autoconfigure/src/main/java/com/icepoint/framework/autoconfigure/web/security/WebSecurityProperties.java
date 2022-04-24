package com.icepoint.framework.autoconfigure.web.security;

import com.icepoint.framework.autoconfigure.web.security.constant.Constants;
import com.icepoint.framework.autoconfigure.web.security.constant.LoginType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Jiawei Zhao
 */
@Data
@ConfigurationProperties(prefix = "icepoint.web.security")
public class WebSecurityProperties {

    private LoginType loginType = LoginType.FORM;

    private Set<String> ignorePaths = new LinkedHashSet<>();

    private static final String[] DEFAULT_IGNORE_PATHS = new String[]{
            Constants.LOGIN_PATH, Constants.REGISTER_PATH
    };

    public Set<String> getIgnorePaths() {
        ignorePaths.addAll(Arrays.asList(DEFAULT_IGNORE_PATHS));
        return ignorePaths;
    }

    public void addIgnorePath(String path) {
        Assert.hasText(path, "路径不能为空");
        ignorePaths.add(path);
    }

    public void addIgnorePaths(Collection<String> paths) {
        if (!CollectionUtils.isEmpty(paths)) {
            paths.forEach(this::addIgnorePath);
        }
    }


}
