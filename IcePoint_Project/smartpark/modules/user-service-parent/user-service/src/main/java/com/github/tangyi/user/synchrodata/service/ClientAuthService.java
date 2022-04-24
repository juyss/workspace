package com.github.tangyi.user.synchrodata.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.tangyi.common.security.ty.MainAccessToken;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.user.synchrodata.config.SynchClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Author xh
 * @Description 客户端授权模式 服务
 * @Date 6:21 2020/11/4
 * @Param
 * @return
 **/
@Service
public class ClientAuthService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String CLIENT_ACCESS_TOKEN_REDIS_KEY = "CLIENT_ACCESS_TOKEN_REDIS_KEY";

    @Autowired
    private SynchClientProperties synchClientProperties;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public MainAccessToken getAccessToken() {
        Object o = redisTemplate.opsForValue().get(CLIENT_ACCESS_TOKEN_REDIS_KEY);
        if (o != null) {
            MainAccessToken accessToken = JsonUtils.toObject(o.toString(), MainAccessToken.class);
            if (accessToken.getCreateTime() + accessToken.getExpiresIn() * 1000 > System.currentTimeMillis()) {
                return accessToken;
            } else {
                //过期，刷新
                return refresh(accessToken);
            }
        }

        String result = restTemplate.getForObject(String.format(synchClientProperties.getAuthUrl() + synchClientProperties.getAuthParam(),
                synchClientProperties.getGrantType(), synchClientProperties.getClientSecret(), synchClientProperties.getClientId()), String.class);
        logger.info("统一认证，客户端模式，获取token: {}", result);

        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            String json = jsonNode.get("data").toString();
            MainAccessToken accessToken = JsonUtils.toObject(json, MainAccessToken.class);
            accessToken.setCreateTime(System.currentTimeMillis());
            redisTemplate.opsForValue().set(CLIENT_ACCESS_TOKEN_REDIS_KEY, JsonUtils.toString(accessToken), accessToken.getExpiresIn() * 10, TimeUnit.SECONDS);
            return accessToken;
        } else {
            throw new CommonException("统一认证，客户端模式，获取token 失败");
        }
    }


    public MainAccessToken refresh(MainAccessToken accessToken) {
        String result = restTemplate.getForObject(String.format(synchClientProperties.getAuthUrl() + synchClientProperties.getRefreshTokenParam()
                , accessToken.getRefreshToken(), synchClientProperties.getClientSecret(), synchClientProperties.getClientId()), String.class);
        logger.info("统一认证，客户端模式，刷新token: {}", result);

        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            String json = jsonNode.get("data").toString();
            MainAccessToken accessToken2 = JsonUtils.toObject(json, MainAccessToken.class);
            accessToken2.setCreateTime(System.currentTimeMillis());
            redisTemplate.opsForValue().set(CLIENT_ACCESS_TOKEN_REDIS_KEY, JsonUtils.toString(accessToken2), accessToken2.getExpiresIn() * 10, TimeUnit.SECONDS);
            return accessToken2;
        }if(jsonNode.get("code").asInt() == 152000){//刷新token失效
            //redisTemplate.opsForValue().set(CLIENT_ACCESS_TOKEN_REDIS_KEY, JsonUtilsJsonUtils.toString(accessToken), 0, TimeUnit.SECONDS);//删除redis的token 报错，过期时间不能设置为0
            redisTemplate.delete(CLIENT_ACCESS_TOKEN_REDIS_KEY);//删除redis的token
            return  getAccessToken();
        }
        else {
            throw new CommonException("统一认证，客户端模式，刷新token 失败");
        }
    }
}
