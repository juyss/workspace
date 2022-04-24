package com.juyss.common.shiro;

import com.juyss.common.exception.BusinessException;
import com.juyss.common.exception.code.BaseResponseCode;
import com.juyss.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: CustomHashedCredentialsMatcher
 * @Desc:
 * @package com.juyss.common.shiro
 * @project manager
 * @date 2021/1/21 14:49
 */
@Slf4j
public class CustomHashedCredentialsMatcher extends SimpleCredentialsMatcher {

    @Lazy
    @Autowired
    private RedisService redisDb;

    @Value("${spring.redis.key.prefix.userToken}")
    private String userTokenPrefix;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String accessToken = (String) token.getPrincipal();
        if (!redisDb.exists(userTokenPrefix + accessToken)) {
            SecurityUtils.getSubject().logout();
            log.info(BaseResponseCode.TOKEN_ERROR.getMsg());
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        return true;
    }
}