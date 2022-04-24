package com.icepoint.base.util;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.user.api.dto.DeptDto;
import com.github.tangyi.user.api.feign.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public final class DeptUtils {

    private static DeptUtils instance;

    private final UserServiceClient userServiceClient;

    private static final ThreadLocal<List<DeptDto>> LOCAL = new ThreadLocal<>();

    @Autowired
    public DeptUtils(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
        DeptUtils.instance = this;
    }

    private static DeptUtils getInstance() {
        return instance;
    }

    @Nullable
    public static List<DeptDto> getDepts() {
        return getInstance().userServiceClient.depts("yuanqu");
    }

}
