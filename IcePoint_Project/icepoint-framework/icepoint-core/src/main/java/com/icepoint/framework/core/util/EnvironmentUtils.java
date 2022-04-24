package com.icepoint.framework.core.util;

import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

/**
 * 应用环境工具类
 *
 * @author Jiawei Zhao
 */
public final class EnvironmentUtils {

    private EnvironmentUtils() {
    }

    public static final Profiles DEV = Profiles.of("dev");

    public static final Profiles TEST = Profiles.of("test");

    public static final Profiles PRO = Profiles.of("pro");


    /**
     * 判断当前的Spring运行环境是否为开发环境
     *
     * @param environment Environment
     * @return 如果是开发环境返回true，否则返回false
     */
    public static boolean isDev(Environment environment) {
        return environment.acceptsProfiles(DEV);
    }

    /**
     * 判断当前的Spring运行环境是否为测试环境
     *
     * @param environment Environment
     * @return 如果是测试环境返回true，否则返回false
     */
    public static boolean isTest(Environment environment) {
        return environment.acceptsProfiles(TEST);
    }

    /**
     * 判断当前的Spring运行环境是否为生产环境
     *
     * @param environment Environment
     * @return 如果是生产环境返回true，否则返回false
     */
    public static boolean isPro(Environment environment) {
        return environment.acceptsProfiles(PRO);
    }

}
