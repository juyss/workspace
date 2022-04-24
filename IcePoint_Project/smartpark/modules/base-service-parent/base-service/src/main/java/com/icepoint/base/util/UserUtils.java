package com.icepoint.base.util;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.user.api.feign.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public final class UserUtils {

    private static UserUtils instance;

    private final UserServiceClient userServiceClient;

    private static final ThreadLocal<UserVo> LOCAL = new ThreadLocal<>();

    @Autowired
    public UserUtils(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
        UserUtils.instance = this;
    }

    private static UserUtils getInstance() {
        return instance;
    }

    @Nullable
    public static UserVo getUser() {
        String username = SysUtil.getUser();
        if (!StringUtils.hasText(username) ||  "anonymousUser".equals(username)) {
            return null;
        } else {
            UserVo user = LOCAL.get();
            if (user == null) {
                user = getInstance().userServiceClient
                        .findUserByIdentifier(username, SysUtil.getTenantCode())
                        .getData();
                LOCAL.set(user);
            }
            return user;
        }

    }


}
